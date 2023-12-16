package com.example.reactnativeapi.util;

import com.example.reactnativeapi.exception.BadRequestException;

public class MessageUtil {
    public static int checkRequest(String request) {
        if(request == null || request.equals("")) {
            throw new BadRequestException("Request must be a Integer");
        }
        try {
            return Integer.parseInt(request);
        } catch (Exception e) {
            throw new BadRequestException("Request must be a Integer");
        }
    }
}
