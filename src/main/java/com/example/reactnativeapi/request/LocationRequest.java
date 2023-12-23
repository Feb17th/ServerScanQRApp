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
public class LocationRequest {
    @Schema(name = "image", example = "image")
    private String image;
    @Schema(name = "name", example = "name")
    private String name;
    @Schema(name = "description", example = "description")
    private String description;
    @Schema(name = "position", example = "position")
    private String position;
}
