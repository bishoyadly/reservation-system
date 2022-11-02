package com.example.reservationsystem.usecases;

import com.example.reservationsystem.adapters.PersonDatabaseAccess;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonUseCaseInteractorFactory {

    public static PersonInputBoundary buildPersonInputBoundary(PersonOutputBoundary outputBoundary, PersonDatabaseAccess databaseAccess) {
        return new PersonUseCaseInteractor(outputBoundary, databaseAccess, new PersonMapperImpl());
    }

}
