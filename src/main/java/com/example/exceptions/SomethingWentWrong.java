package com.example.exceptions;

public class SomethingWentWrong extends RuntimeException {
    public SomethingWentWrong(String message) {
        super(message);
    }
}
