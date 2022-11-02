package com.example.reservationsystem.adapters;

import com.example.reservationsystem.entities.Person;
import com.example.reservationsystem.usecases.PersonDataAccess;
import org.springframework.stereotype.Component;

@Component
public class PersonDatabaseAccess implements PersonDataAccess {

    private final PersonRepository personRepository;
    private final PersonRecordMapper personRecordMapper;

    public PersonDatabaseAccess(PersonRepository personRepository, PersonRecordMapper personRecordMapper) {
        this.personRepository = personRepository;
        this.personRecordMapper = personRecordMapper;
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(personRecordMapper.personToRecord(person));
    }
}
