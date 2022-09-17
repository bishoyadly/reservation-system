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

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidNationalId(String nationalId) {
        personRequestModel.setNationalId(nationalId);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidName(String name) {
        personRequestModel.setName(name);

        personInputBoundary.savePerson(personRequestModel);

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NAME_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 14})
    void savePerson_presentBadRequest_caseInvalidAge(Integer age) {
        personRequestModel.setAge(age);
        personInputBoundary.savePerson(personRequestModel);
        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_AGE);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidEmailAddress(String emailAddress) {
        personRequestModel.setEmailAddress(emailAddress);

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
    void savePerson_presentBadRequest_caseInvalidMobileNumber(String mobileNumber) {
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
