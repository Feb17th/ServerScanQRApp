package com.example.reactnativeapi.controller;

import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.response.MessageResponse;
import com.example.reactnativeapi.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @Operation(summary = "Get All History")
    @GetMapping("")
    public ResponseEntity<ListResponse> getAllHistory(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(historyService.getAllHistory(request));
    }

    @Operation(summary = "Delete One History")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Put Id of History to delete this History")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> getAllHistory(
            HttpServletRequest request,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(historyService.deleteHistory(request, id));
    }

    @Operation(summary = "Star or Delete Star this Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Put Id of History to save this Location")
    })
    @PutMapping("/star/{id}")
    public ResponseEntity<MessageResponse> starLocation(
            HttpServletRequest request,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(historyService.starLocation(request, id));
    }

    @Operation(summary = "Get All Star Location")
    @GetMapping("/star")
    public ResponseEntity<ListResponse> getAllStarLocation(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(historyService.getAllStarLocation(request));
    }
}
