package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

class PersonUseCaseInteractor implements PersonInputBoundary {

    private final PersonOutputBoundary personOutputBoundary;
    private final PersonDataAccess personDataAccess;
    private final PersonMapper personMapper;

    public PersonUseCaseInteractor(PersonOutputBoundary personOutputBoundary, PersonDataAccess personDataAccess, PersonMapper personMapper) {
        this.personOutputBoundary = personOutputBoundary;
        this.personDataAccess = personDataAccess;
        this.personMapper = personMapper;
    }

    @Override
    public Object savePerson(PersonRequest personRequest) {
        validatePersonRequestFields(personRequest);
        Person person = personMapper.personRequestToPerson(personRequest);
        personDataAccess.savePerson(person);
        PersonResponse responseModel = personMapper.personToResponseModel(person);
        return personOutputBoundary.presentSuccessResponse(responseModel);
    }

    private void validatePersonRequestFields(PersonRequest personRequest) {
        if (StringUtils.isBlank(personRequest.getNationalId()))
            presentBadRequestError(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        if (StringUtils.isBlank(personRequest.getName()))
            presentBadRequestError(PersonErrorMessages.NAME_IS_REQUIRED);
        if (personRequest.getAge() < 15)
            presentBadRequestError(PersonErrorMessages.INVALID_AGE);
        if (StringUtils.isBlank(personRequest.getEmailAddress()))
            presentBadRequestError(PersonErrorMessages.EMAIL_ADDRESS_IS_REQUIRED);
        if (!EmailValidator.getInstance().isValid(personRequest.getEmailAddress()))
            presentBadRequestError(PersonErrorMessages.INVALID_EMAIL_ADDRESS);
        if (StringUtils.isBlank(personRequest.getMobileNumber()))
            presentBadRequestError(PersonErrorMessages.INVALID_MOBILE_NUMBER);
    }

    private void presentBadRequestError(String errorMessage) {
        personOutputBoundary.presentBadRequest(errorMessage);
    }

}
