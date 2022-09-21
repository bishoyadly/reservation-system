package com.example.reservationsystem.usecases;

public class PersonApiException extends RuntimeException {
    public PersonApiException(String message) {
        super(message);
    }
}
