package com.kernelpanic.hackathonbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException() {
    }

    public DuplicateResourceException(String message){
        super(message);
    }
}