package com.example.reactnativeapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {
    private String message;
    private String location;
    private LocalDateTime lastUpdate;
}
