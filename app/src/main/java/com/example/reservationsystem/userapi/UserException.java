package com.example.reservationsystem.userapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public UserException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
