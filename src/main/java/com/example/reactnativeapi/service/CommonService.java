package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.UserInfoEntity;
import com.example.reactnativeapi.entity.UsersEntity;
import com.example.reactnativeapi.exception.BadRequestException;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.repository.UserInfoRepository;
import com.example.reactnativeapi.repository.UsersRepository;
import com.example.reactnativeapi.request.ChangePasswordRequest;
import com.example.reactnativeapi.request.UpdateUserInfoRequest;
import com.example.reactnativeapi.response.EncodedJwtResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.response.UserInfoResponse;
import com.example.reactnativeapi.util.GeneralUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final UserInfoRepository userInfoRepository;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoResponse getUserInfo(HttpServletRequest request) {
        EncodedJwtResponse encodedJwtResponse = GeneralUtil.encodedJwtToken(request);

        UUID userId = encodedJwtResponse.getUserId();

        UserInfoEntity userInfo = userInfoRepository.findInfoByUserId(userId);

        return new UserInfoResponse(
                userInfo.getImage(),
                userInfo.getName(),
                userInfo.getPhoneNumber(),
                userInfo.getAddress()
        );
    }

    public MessageResponse updateUserInfo(HttpServletRequest request, UpdateUserInfoRequest updateUserInfoRequest) {
        EncodedJwtResponse encodedJwtResponse = GeneralUtil.encodedJwtToken(request);

        UUID userId = encodedJwtResponse.getUserId();

        UserInfoEntity userInfo = userInfoRepository.findInfoByUserId(userId);
        userInfo.setImage(updateUserInfoRequest.getImage());
        userInfo.setName(updateUserInfoRequest.getName());
        userInfo.setPhoneNumber(updateUserInfoRequest.getPhoneNumber());
        userInfo.setAddress(updateUserInfoRequest.getAddress());

        userInfoRepository.save(userInfo);

        return new MessageResponse("Update Info Successfully!");
    }

    public MessageResponse changePasswordResponse(HttpServletRequest request, ChangePasswordRequest changePasswordRequest) {
        EncodedJwtResponse encodedJwtResponse = GeneralUtil.encodedJwtToken(request);
        UUID userId = encodedJwtResponse.getUserId();

        UsersEntity users = userRepository.findById(userId);

        if (users == null) {
            throw new NotFoundException("Not found user");
        }

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), users.getPass())) {
            throw new BadRequestException("The current password is incorrect");
        }

        if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getCurrentPassword())) {
            throw new BadRequestException("You're using an old password");
        }
        if (!changePasswordRequest.getNewPassword().matches(changePasswordRequest.getConfirmNewPassword())) {
            throw new BadRequestException("No overlap");
        }

        users.setPass(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(users);

        return new MessageResponse("Change password successfully!");
    }

}
