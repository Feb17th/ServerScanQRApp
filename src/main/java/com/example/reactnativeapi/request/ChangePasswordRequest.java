package com.example.reactnativeapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @Schema(name = "currentPassword", example = "pass123")
    private String currentPassword;
    @Schema(name = "newPassword", example = "newPass123")
    private String newPassword;
    @Schema(name = "confirmNewPassword", example = "newPass123")
    private String confirmNewPassword;
}
