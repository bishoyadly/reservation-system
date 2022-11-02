package com.example.reservationsystem.adapters;

import com.example.reservationsystem.entities.Person;
import com.example.reservationsystem.usecases.PersonDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class PersonDatabaseAccessTest {

    @Autowired
    private PersonRepository personRepository;

    private PersonDataAccess dataAccess;

    private Person buildPerson() {
        Person person = new Person();
        person.setNationalId("nationalId");
        person.setName("name");
        person.setAge(15);
        person.setEmailAddress("test_email_address@mail.com");
        person.setMobileNumber("12345");
        return person;
    }

    @BeforeEach
    void setUp() {
        PersonRecordMapper personRecordMapper = new PersonRecordMapperImpl();
        dataAccess = new PersonDatabaseAccess(personRepository, personRecordMapper);
    }

    @Test
    void savePerson() {
        Person person = buildPerson();
        dataAccess.savePerson(person);
        assertTrue(personRepository.existsById(person.getNationalId()));
    }
}
