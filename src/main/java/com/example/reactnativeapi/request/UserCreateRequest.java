package com.example.reactnativeapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @Schema(name = "userName", example = "customer")
    private String userName;
    @Schema(name = "password", example = "pass123")
    private String password;
    @Schema(name = "role", example = "Customer")
    private String role;
}
