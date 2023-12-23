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
public class AuthenticationRequest {
    @Schema(name = "userName", example = "customer")
    private String userName;
    @Schema(name = "password", example = "pass123")
    private String password;
}
