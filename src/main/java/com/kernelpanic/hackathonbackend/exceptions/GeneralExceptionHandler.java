package com.kernelpanic.hackathonbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ResourceAccessDeniedException.class,
            DuplicateResourceException.class
    })
    public ResponseEntity<String> handleConflictException(
            Exception e
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(
            Exception e
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
