package com.cources.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Object already in use")
public class ClassAlreadyExistsException extends RuntimeException {
    private String message;

    public ClassAlreadyExistsException (String message) {
        super(message);
        this.message = message;
    }

    public ClassAlreadyExistsException () {

    }
}
