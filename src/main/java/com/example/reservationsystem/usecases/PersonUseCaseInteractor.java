package com.example.reservationsystem.usecases;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonUseCaseInteractor implements PersonInputBoundary {

    private PersonOutputBoundary personOutputBoundary;
    private PersonDataAccess personDataAccess;

    @Autowired
    public PersonUseCaseInteractor(PersonOutputBoundary personOutputBoundary, PersonDataAccess personDataAccess) {
        this.personOutputBoundary = personOutputBoundary;
        this.personDataAccess = personDataAccess;
    }

    @Override
    public Object savePerson(PersonRequestModel personRequestModel) {
        if (StringUtils.isBlank(personRequestModel.getNationalId()))
            personOutputBoundary.presentBadRequest(PersonErrorMessages.NATIONAL_ID_IS_REQUIRED);
        if (StringUtils.isBlank(personRequestModel.getName()))
            personOutputBoundary.presentBadRequest(PersonErrorMessages.NAME_IS_REQUIRED);
        if (personRequestModel.getAge() < 15)
            personOutputBoundary.presentBadRequest(PersonErrorMessages.INVALID_AGE);
        if (StringUtils.isBlank(personRequestModel.getEmailAddress()))
            personOutputBoundary.presentBadRequest(PersonErrorMessages.EMAIL_ADDRESS_IS_REQUIRED);
        if (!EmailValidator.getInstance().isValid(personRequestModel.getEmailAddress()))
            personOutputBoundary.presentBadRequest(PersonErrorMessages.INVALID_EMAIL_ADDRESS);

        return null;
    }
}
