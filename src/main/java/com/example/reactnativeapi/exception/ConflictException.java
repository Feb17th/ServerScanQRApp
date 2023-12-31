package com.example.reactnativeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
    private final String message;

    public ConflictException(String message) {
        super(message);
        this.message = message;
    }
}
