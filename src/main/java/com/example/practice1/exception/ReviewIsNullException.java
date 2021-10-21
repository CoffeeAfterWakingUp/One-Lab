package com.example.practice1.exception;

public class ReviewIsNullException extends RuntimeException {
    public ReviewIsNullException(String message) {
        super(message);
    }
}
