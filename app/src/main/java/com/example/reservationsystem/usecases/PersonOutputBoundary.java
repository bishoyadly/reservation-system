package com.example.reservationsystem.usecases;

public interface PersonOutputBoundary {
    Object presentBadRequest(String errorMessage);

    Object presentSuccessResponse(PersonResponseModel responseModel);
}
