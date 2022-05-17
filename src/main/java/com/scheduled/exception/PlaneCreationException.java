package com.scheduled.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class PlaneCreationException extends RuntimeException{

    private String message;

    public PlaneCreationException(String message) {
        super(String.format("Error occurred while creating the plane. ", message));
        this.message = message;
    }
}