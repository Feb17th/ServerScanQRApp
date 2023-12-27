package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.HistoryEntity;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.repository.HistoryRepository;
import com.example.reactnativeapi.repository.LocationRepository;
import com.example.reactnativeapi.response.EncodedJwtResponse;
import com.example.reactnativeapi.response.HistoryResponse;
import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.util.GeneralUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final LocationRepository locationRepository;

    public ListResponse getAllHistory(HttpServletRequest request) {
        EncodedJwtResponse encodedJwtResponse = GeneralUtil.encodedJwtToken(request);

        UUID userId = encodedJwtResponse.getUserId();
        List<HistoryEntity> listHistory = historyRepository.findAllHistory(userId);

        List<HistoryResponse> listResponse = new ArrayList<>();
        for(HistoryEntity loop : listHistory) {
            String name = locationRepository.findOneById(loop.getLocationId()).getName();
            listResponse.add(new HistoryResponse(
                    loop.getId(),
                    name,
                    loop.getIsSaving(),
                    loop.getTimeScan()
            ));
        }

        return new ListResponse(listResponse);
    }

    public MessageResponse deleteHistory(HttpServletRequest request, String idFromPathVariable) {
        int id = GeneralUtil.checkRequest(idFromPathVariable);

        HistoryEntity historyEntity = historyRepository.findOneById(id);
        if(historyEntity == null) {
            throw new NotFoundException("You haven't scanned this QR Code.");
        }
        historyEntity.setIsDeleted(true);

        historyRepository.save(historyEntity);

        return new MessageResponse("Delete Successfully!");
    }

    public MessageResponse starLocation(HttpServletRequest request, String idFromPathVariable) {
        int id = GeneralUtil.checkRequest(idFromPathVariable);

        HistoryEntity historyEntity = historyRepository.findOneById(id);
        if(historyEntity == null) {
            throw new NotFoundException("You haven't scanned this QR Code.");
        }
        historyEntity.setIsSaving(true);

        historyRepository.save(historyEntity);

        return new MessageResponse("Star Successfully!");
    }

    public ListResponse getAllStarLocation(HttpServletRequest request) {
        EncodedJwtResponse encodedJwtResponse = GeneralUtil.encodedJwtToken(request);

        UUID userId = encodedJwtResponse.getUserId();
        List<HistoryEntity> listHistory = historyRepository.findAllStarLocation(userId);

        List<HistoryResponse> listResponse = new ArrayList<>();
        for(HistoryEntity loop : listHistory) {
            String name = locationRepository.findOneById(loop.getLocationId()).getName();
            listResponse.add(new HistoryResponse(
                    loop.getId(),
                    name,
                    loop.getIsSaving(),
                    loop.getTimeScan()
            ));
        }

        return new ListResponse(listResponse);
    }
}
