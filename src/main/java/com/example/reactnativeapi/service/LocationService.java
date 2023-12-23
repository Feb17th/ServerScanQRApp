package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.LocationEntity;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.repository.LocationRepository;
import com.example.reactnativeapi.request.LocationRequest;
import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.response.LocationResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public MessageResponse helloWorld() {
        return new MessageResponse("Hello World!");
    }

    public LocationResponse getLocation(String locationIdFromPathVariable) {
        UUID locationId = GeneralUtil.checkUUID(locationIdFromPathVariable);

        LocationEntity location = locationRepository.findOneById(locationId);
        if(location == null) {
            throw new NotFoundException("Can't find location or QR code is wrong.");
        }

        return new LocationResponse(
                location.getImage(),
                location.getName(),
                location.getDescription(),
                location.getPosition()
        );
    }

    public MessageResponse addLocation(LocationRequest locationRequest) {
        LocationEntity location = new LocationEntity(
                locationRequest.getImage(),
                locationRequest.getName(),
                locationRequest.getDescription(),
                locationRequest.getPosition()
        );

        locationRepository.save(location);
        return new MessageResponse("Add Successfully!");
    }

    public ListResponse getAllLocation() {
        List<LocationEntity> list = locationRepository.findAllLocation();

        return new ListResponse(list);
    }
}
