package com.example.reactnativeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    private final String message;

    public ForbiddenException(String message) {
        super(message);
        this.message = message;
    }
}
