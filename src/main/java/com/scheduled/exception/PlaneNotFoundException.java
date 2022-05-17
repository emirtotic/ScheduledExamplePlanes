package com.scheduled.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class PlaneNotFoundException extends RuntimeException {

    private int id;

    public PlaneNotFoundException(int id) {
        super(String.format("Plane not found with name %s ", id));
        this.id = id;
    }
}