package com.example.reservationsystem.usecases;

import com.example.reservationsystem.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person personRequestToPerson(PersonRequestModel requestModel);

    PersonResponseModel personToResponseModel(Person person);
}
