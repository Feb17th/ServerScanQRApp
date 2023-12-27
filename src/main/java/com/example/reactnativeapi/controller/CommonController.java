package com.example.reactnativeapi.controller;

import com.example.reactnativeapi.request.UpdateUserInfoRequest;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.response.UserInfoResponse;
import com.example.reactnativeapi.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/common")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Operation(summary = "Get Info User")
    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(commonService.getUserInfo(request));
    }

    @Operation(summary = "Update User Info")
    @PutMapping("/info")
    public ResponseEntity<MessageResponse> updateUserInfo(
            HttpServletRequest request,
            @RequestBody UpdateUserInfoRequest updateUserInfoRequest
    ) {
        return ResponseEntity.ok(commonService.updateUserInfo(request, updateUserInfoRequest));
    }
}
