package com.example.exceptions;

public class HouseImageNotFoundException extends RuntimeException {
    public HouseImageNotFoundException(String message) {
        super(message);
    }
}
