package com.example.reservationsystem.adapters;

import com.example.reservationsystem.usecases.PersonOutputBoundary;
import com.example.reservationsystem.usecases.PersonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PersonPresenter implements PersonOutputBoundary {
    @Override
    public Object presentBadRequest(String errorMessage) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @Override
    public Object presentSuccessResponse(PersonResponse responseModel) {
        return ResponseEntity.ok(responseModel);
    }
}
