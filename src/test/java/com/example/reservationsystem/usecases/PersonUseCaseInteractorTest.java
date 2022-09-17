package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonUseCaseInteractorTest {

    @Mock
    private PersonOutputBoundary personOutputBoundary;

    @Mock
    private PersonDataAccess personDataAccess;

    private PersonInputBoundary personInputBoundary;

    private PersonRequestModel personRequestModel;

    @BeforeEach
    void setUp() {
        personInputBoundary = new PersonUseCaseInteractor(personOutputBoundary, personDataAccess);
        personRequestModel = buildPersonRequestModel();
    }

    @Test
    void savePerson_presentBadRequest_caseNullNationalId() {
        personRequestModel.setNationalId(null);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseBlankNationalId() {
        personRequestModel.setNationalId("");

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }


    @Test
    void savePerson_presentBadRequest_caseNullName() {
        personRequestModel.setName(null);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NAME_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseBlankName() {
        personRequestModel.setName("");

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NAME_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseInvalidAge() {
        personRequestModel.setAge(-1);
        personInputBoundary.savePerson(personRequestModel);
        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_AGE);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));

        personRequestModel.setAge(0);
        personInputBoundary.savePerson(personRequestModel);
        verify(personOutputBoundary, times(2)).presentBadRequest(PersonErrorMessages.INVALID_AGE);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));

        personRequestModel.setAge(14);
        personInputBoundary.savePerson(personRequestModel);
        verify(personOutputBoundary, times(3)).presentBadRequest(PersonErrorMessages.INVALID_AGE);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseNullEmailAddress() {
        personRequestModel.setEmailAddress(null);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.EMAIL_ADDRESS_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseBlankEmailAddress() {
        personRequestModel.setEmailAddress("");

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.EMAIL_ADDRESS_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseInvalidEmailAddress() {
        personRequestModel.setEmailAddress("invalid_email_address");

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_EMAIL_ADDRESS);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseNullMobileNumber(String mobileNumber) {
        personRequestModel.setMobileNumber(mobileNumber);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_MOBILE_NUMBER);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    private PersonRequestModel buildPersonRequestModel() {
        PersonRequestModel personRequestModel = new PersonRequestModel();
        personRequestModel.setNationalId("nationalId");
        personRequestModel.setName("name");
        personRequestModel.setAge(15);
        personRequestModel.setEmailAddress("test_email_address@mail.com");
        personRequestModel.setMobileNumber("12345");
        return personRequestModel;
    }

}
