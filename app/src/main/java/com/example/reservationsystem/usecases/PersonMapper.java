package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person personRequestToPerson(PersonRequest requestModel);

    PersonResponse personToResponseModel(Person person);
}
