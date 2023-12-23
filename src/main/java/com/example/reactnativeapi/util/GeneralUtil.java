package com.example.reactnativeapi.util;

import com.example.reactnativeapi.exception.BadRequestException;

import java.util.UUID;

public class GeneralUtil {
    public static UUID checkUUID(String idFromPathVariable){
        try {
            return UUID.fromString(idFromPathVariable);
        } catch (Exception e) {
            throw new BadRequestException("Id from path variable is not uuid.");
        }
    }
}
