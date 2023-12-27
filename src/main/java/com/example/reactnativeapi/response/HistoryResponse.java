package com.example.reactnativeapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {
    private Integer id;
    private String name;
    private Boolean isSaving;
    private LocalDateTime timeScan;
}
