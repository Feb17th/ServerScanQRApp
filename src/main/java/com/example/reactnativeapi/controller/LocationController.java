package com.example.reactnativeapi.controller;

import com.example.reactnativeapi.request.LocationRequest;
import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.response.LocationResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get One Location By Id")
    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getLocation(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

    @Operation(summary = "Add One Location")
    @PostMapping()
    public ResponseEntity<MessageResponse> addLocation(
            @RequestBody LocationRequest locationRequest
    ){
        return ResponseEntity.ok(locationService.addLocation(locationRequest));
    }

    @Operation(summary = "Get All Location")
    @GetMapping("")
    public ResponseEntity<ListResponse> getAllLocation(
    ) {
        return ResponseEntity.ok(locationService.getAllLocation());
    }
}
