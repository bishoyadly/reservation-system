package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;

public interface PersonDataAccess {
    Person savePerson(Person person);

    boolean personExistsByNationalId(String nationalId);
}
