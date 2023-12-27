package com.example.reactnativeapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {
    @Schema(name = "image", example = "image")
    private String image;
    @Schema(name = "name", example = "Leo Messi")
    private String name;
    @Schema(name = "phoneNumber", example = "0707829902")
    private String phoneNumber;
    @Schema(name = "address", example = "Cơ sở Dĩ An: Khu phố Tân Lập, Phường Đông Hòa, TP. Dĩ An, Tỉnh Bình Dương")
    private String address;
}
