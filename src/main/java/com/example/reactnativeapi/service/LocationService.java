package com.example.reactnativeapi.service;

import com.example.reactnativeapi.repository.LocationRepository;
import com.example.reactnativeapi.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public MessageResponse helloWorld() {
        return new MessageResponse("Hello World!");
    }
}
