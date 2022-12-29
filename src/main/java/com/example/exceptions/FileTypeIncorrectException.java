package com.example.exceptions;

public class FileTypeIncorrectException extends RuntimeException {

    public FileTypeIncorrectException(String message) {
        super(message);
    }
}
