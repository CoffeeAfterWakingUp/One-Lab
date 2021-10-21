package com.example.practice1.exception;

public class AuthorIsNullException extends RuntimeException {
    public AuthorIsNullException(String message) {
        super(message);
    }
}
