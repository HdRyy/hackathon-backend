package com.kernelpanic.hackathonbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAccessDeniedException extends RuntimeException{
    public ResourceAccessDeniedException() {
    }

    public ResourceAccessDeniedException(String message){
        super(message);
    }
}