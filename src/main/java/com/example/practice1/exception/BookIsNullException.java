package com.example.practice1.exception;

public class BookIsNullException extends RuntimeException {

    public BookIsNullException(String message) {
        super(message);
    }
}
