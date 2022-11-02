package com.example.reservationsystem.adapters;

import com.example.reservationsystem.usecases.PersonInputBoundary;
import com.example.reservationsystem.usecases.PersonOutputBoundary;
import com.example.reservationsystem.usecases.PersonUseCaseInteractorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAdaptersSetup {

    @Bean
    public PersonInputBoundary personInputBoundary(PersonOutputBoundary outputBoundary, PersonDatabaseAccess databaseAccess) {
        return PersonUseCaseInteractorFactory.buildPersonInputBoundary(outputBoundary, databaseAccess);
    }
}
