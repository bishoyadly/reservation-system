package com.example.reservationsystem.adapters;

import com.example.reservationsystem.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonRecordMapper {
    PersonRecord personToRecord(Person person);
}
