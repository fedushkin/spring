package com.cources.jpa.handler;

import com.cources.jpa.exception.ClassAlreadyExistsException;
import com.cources.jpa.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BlogExceptionHandler.class);

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<Object> blogNotFoundException (ObjectNotFoundException ex) {
        logger.error("Blog Not Found Exception : " + ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ClassAlreadyExistsException.class})
    public ResponseEntity<Object> blogAlreadyExistsException (ClassAlreadyExistsException ex) {
        logger.error("Blog Already Exists Exception : " + ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}