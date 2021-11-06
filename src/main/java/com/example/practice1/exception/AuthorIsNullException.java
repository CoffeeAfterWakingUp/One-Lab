package com.example.practice1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AuthorIsNullException extends RuntimeException {
    public AuthorIsNullException(String message) {
        super(message);
    }
}
