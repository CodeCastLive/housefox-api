package com.example.exceptions;

public class HouseLikeNotFoundException extends RuntimeException {
    public HouseLikeNotFoundException(String message) {
        super(message);
    }
}
