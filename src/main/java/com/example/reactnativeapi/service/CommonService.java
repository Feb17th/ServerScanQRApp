package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.UserInfoEntity;
import com.example.reactnativeapi.repository.UserInfoRepository;
import com.example.reactnativeapi.request.UpdateUserInfoRequest;
import com.example.reactnativeapi.response.EncodedJwtResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.response.UserInfoResponse;
import com.example.reactnativeapi.util.GeneralUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final UserInfoRepository userInfoRepository;

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
}
