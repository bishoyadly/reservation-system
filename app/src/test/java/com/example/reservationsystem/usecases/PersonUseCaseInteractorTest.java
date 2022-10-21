package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonUseCaseInteractorTest {

    @Mock
    private PersonOutputBoundary personOutputBoundary;

    @Mock
    private PersonDataAccess personDataAccess;

    private PersonInputBoundary personInputBoundary;

    private PersonRequestModel personRequestModel;

    private PersonMapper personMapper;

    @BeforeEach
    void setUp() {
        personMapper = new PersonMapperImpl();
        personInputBoundary = new PersonUseCaseInteractor(personOutputBoundary, personDataAccess, personMapper);
        personRequestModel = buildPersonRequestModel();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidNationalId(String nationalId) {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setNationalId(nationalId);

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidName(String name) {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setName(name);

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.NAME_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 14})
    void savePerson_presentBadRequest_caseInvalidAge(Integer age) {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setAge(age);

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_AGE);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseEmailAddressNotProvided(String emailAddress) {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setEmailAddress(emailAddress);

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.EMAIL_ADDRESS_IS_REQUIRED);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_presentBadRequest_caseInvalidEmailAddress() {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setEmailAddress("invalid_email_address");

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_EMAIL_ADDRESS);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void savePerson_presentBadRequest_caseInvalidMobileNumber(String mobileNumber) {
        when(personOutputBoundary.presentBadRequest(anyString())).thenThrow(PersonApiException.class);
        personRequestModel.setMobileNumber(mobileNumber);

        assertThrows(PersonApiException.class, () -> personInputBoundary.savePerson(personRequestModel));

        verify(personOutputBoundary, times(1)).presentBadRequest(PersonErrorMessages.INVALID_MOBILE_NUMBER);
        verify(personDataAccess, times(0)).savePerson(any(Person.class));
    }

    @Test
    void savePerson_caseValidRequest() {
        personInputBoundary.savePerson(personRequestModel);
        assertPassedPerson();
        assertPassedPersonResponseModel();
        verify(personDataAccess, times(1)).savePerson(any(Person.class));
    }

    private void assertPassedPerson() {
        verify(personDataAccess, times(1)).savePerson(any(Person.class));
        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personDataAccess).savePerson(argumentCaptor.capture());
        assertPersonRequestMappedFields(personRequestModel, argumentCaptor.getValue());
    }

    private void assertPersonRequestMappedFields(PersonRequestModel requestModel, Person person) {
        assertEquals(person.getNationalId(), requestModel.getNationalId());
        assertEquals(requestModel.getName(), person.getName());
        assertEquals(requestModel.getAge(), person.getAge());
        assertEquals(requestModel.getMobileNumber(), person.getMobileNumber());
        assertEquals(requestModel.getEmailAddress(), person.getEmailAddress());
    }

    private void assertPassedPersonResponseModel() {
        verify(personOutputBoundary, times(1)).presentSuccessResponse(any(PersonResponseModel.class));
        ArgumentCaptor<PersonResponseModel> argumentCaptor = ArgumentCaptor.forClass(PersonResponseModel.class);
        verify(personOutputBoundary).presentSuccessResponse(argumentCaptor.capture());
        Person person = personMapper.personRequestToPerson(personRequestModel);
        assertPersonResponseMappedFields(person, argumentCaptor.getValue());
    }

    private void assertPersonResponseMappedFields(Person person, PersonResponseModel responseModel) {
        assertEquals(person.getNationalId(), responseModel.getNationalId());
        assertEquals(person.getName(), responseModel.getName());
        assertEquals(person.getAge(), responseModel.getAge());
        assertEquals(person.getMobileNumber(), responseModel.getMobileNumber());
        assertEquals(person.getEmailAddress(), responseModel.getEmailAddress());
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
