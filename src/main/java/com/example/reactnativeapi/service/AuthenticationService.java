package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.*;
import com.example.reactnativeapi.exception.BadRequestException;
import com.example.reactnativeapi.exception.ConflictException;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.repository.*;
import com.example.reactnativeapi.request.AuthenticationRequest;
import com.example.reactnativeapi.request.UserCreateRequest;
import com.example.reactnativeapi.response.AuthenticationResponse;
import com.example.reactnativeapi.response.MessageResponse;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.reactnativeapi.util.AuthenticateUtil.checkRole;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;
    private final UserRoleRepository userRoleRepository;
    private final RolesRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenCategoryRepository tokenCategoryRepository;
    private final TokenTypeRepository tokenTypeRepository;
    private final AuthenticationManager authenticationManager;
    private final UserInfoRepository userInfoRepository;

    public MessageResponse register(UserCreateRequest userCreateRequest){
        if(userCreateRequest.getUserName().equals("") || userCreateRequest.getPassword().equals("")) {
            throw new BadRequestException("Email or Password must not be empty.");
        }

        UsersEntity userCheck = userRepository.findByUsername(userCreateRequest.getUserName());
        if (userCheck != null)
            throw new ConflictException("User name or email already exists");

        if(checkRole(userCreateRequest.getRole()) == null) {
            throw new NotFoundException("Role doesn't exist in System!");
        }

        var user = UsersEntity.builder()
                .username(userCreateRequest.getUserName())
                .isDeleted(false)
                .pass(passwordEncoder.encode(userCreateRequest.getPassword()))
                .build();
        var savedUser = userRepository.save(user);

        userRoleRepository.save(new UserRoleEntity(savedUser.getId(), checkRole(userCreateRequest.getRole())));

        userInfoRepository.save(new UserInfoEntity(
                savedUser.getId(),
                null,
                null,
                null,
                null
        ));

        return new MessageResponse("Register successfully!");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsersEntity user = userRepository.findByUsername(request.getUserName());
        if(user == null) {
            throw new NotFoundException("Not found user name or email");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        MyUserDetails myUserDetails = myUserDetailsService.createMyUserDetails(user);
        var jwtToken = jwtService.generateToken(myUserDetails, user.getId().toString(), getUserRole(user.getId()));
        var refreshToken = jwtService.generateRefreshToken(myUserDetails, user.getId().toString(), getUserRole(user.getId()));
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken, "ACCESS");
        saveUserToken(user, refreshToken, "REFRESH");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse refreshToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("header is null or didn't not start with Bearer");
        }
        refreshToken = authHeader.substring(7);
        userName = jwtService.extractUsername(refreshToken);


        if (userName != null) {
            UsersEntity user = userRepository.findByUsername(userName);
            if(user == null) {
                throw new NotFoundException("Can't not found User");
            }
            tokenRepository.findByTokenAndAndTokenCategoryIdAndExpiredFalseAndRevokedFalse(refreshToken, tokenCategoryRepository.findCategoryByTokenCategoryName("REFRESH").getId())
                    .orElseThrow(() -> new NotFoundException("REFRESH token not found or expired"));
            MyUserDetails myUserDetails = myUserDetailsService.createMyUserDetails(user);
            if (jwtService.isTokenValid(refreshToken, myUserDetails)) {
                var accessToken = jwtService.generateToken(myUserDetails, user.getId().toString(), getUserRole(user.getId()));
                revokeAllUserTokensAccess(user);
                saveUserToken(user, accessToken, "ACCESS");
                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }

        }
        throw new MalformedJwtException("token invalid");
    }

    public void saveUserToken(UsersEntity user, String jwtToken, String tokenCategory) {
        var token = TokenEntity.builder()
                .userId(user.getId())
                .token(jwtToken)
                .tokenTypeId(tokenTypeRepository.findTypeByTokenTypeName("BEARER").getId())
                .expired(false)
                .revoked(false)
                .tokenCategoryId(tokenCategoryRepository.findCategoryByTokenCategoryName(tokenCategory).getId())
                .build();
        tokenRepository.save(token);
    }

    public List<String> getUserRole(UUID userId) {
        List<String> roles = new ArrayList<>();
        List<UserRoleEntity> userRoleEntities = userRoleRepository.findByUserId(userId);
        //create CANDIDATE role if account don't have role
        if (userRoleEntities.size() == 0) {
            UserRoleEntity newUserRoleEntity = new UserRoleEntity(userId, 1);
            userRoleRepository.save(newUserRoleEntity);
            userRoleEntities = userRoleRepository.findByUserId(userId);
        }
        // get roles list
        for (UserRoleEntity userRole : userRoleEntities) {
            RolesEntity roleEntity = roleRepository.findOneById(userRole.getRoleId());
            roles.add(roleEntity.getRoleName());
        }
        return roles;
    }

    public void revokeAllUserTokens(UsersEntity user) {
        var validUserTokens = tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void revokeAllUserTokensAccess(UsersEntity user) {
        var validUserTokens = tokenRepository.findAllByUserIdAndTokenCategoryIdAndExpiredFalseAndRevokedFalse(user.getId(), tokenCategoryRepository.findCategoryByTokenCategoryName("ACCESS").getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
