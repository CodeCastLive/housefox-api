package com.example.exceptions;

public class CouldNotRead extends RuntimeException {

    public CouldNotRead(String message) {
        super(message);
    }
}
