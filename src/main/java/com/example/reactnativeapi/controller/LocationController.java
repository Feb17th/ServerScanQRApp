package com.example.reactnativeapi.controller;

import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.service.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/hello-world")
    public ResponseEntity<MessageResponse> register() {
        return ResponseEntity.ok(locationService.helloWorld());
    }
}
