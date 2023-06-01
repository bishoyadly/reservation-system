package com.example.reservationsystem.userapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    protected ResponseEntity<Object> handleUserException(UserException exception, WebRequest request) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
