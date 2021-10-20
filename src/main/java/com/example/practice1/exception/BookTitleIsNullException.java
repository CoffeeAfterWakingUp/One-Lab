package com.example.practice1.exception;

public class BookTitleIsNullException extends RuntimeException {

    public BookTitleIsNullException() {
        super("Book title can not be null");
    }
}
