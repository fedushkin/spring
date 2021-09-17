package com.cources.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found exception")
public class ObjectNotFoundException extends RuntimeException {
    private String message;

    public ObjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ObjectNotFoundException() {

    }
}