package com.example.reservationsystem.adapters;

import com.example.reservationsystem.usecases.PersonErrorMessages;
import com.example.reservationsystem.usecases.PersonOutputBoundary;
import com.example.reservationsystem.usecases.PersonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class PersonPresenterTest {

    private PersonOutputBoundary personPresenter;

    @BeforeEach
    void setUp() {
        personPresenter = new PersonPresenter();
    }

    private PersonResponse buildPersonResponseModel() {
        PersonResponse responseModel = new PersonResponse();
        responseModel.setNationalId("nationalId");
        responseModel.setName("name");
        responseModel.setAge(15);
        responseModel.setEmailAddress("test_email_address@mail.com");
        responseModel.setMobileNumber("12345");
        return responseModel;
    }

    @Test
    void presentBadRequest() {
        ResponseStatusException exception = null;
        try {
            personPresenter.presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        } catch (ResponseStatusException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED, exception.getReason());
    }

    @Test
    void presentSuccessResponse() {
        PersonResponse responseModel = buildPersonResponseModel();
        Object response = personPresenter.presentSuccessResponse(responseModel);
        assertTrue(response instanceof ResponseEntity);
        ResponseEntity<PersonResponse> entity = (ResponseEntity<PersonResponse>) response;
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(responseModel, entity.getBody());
    }
}
