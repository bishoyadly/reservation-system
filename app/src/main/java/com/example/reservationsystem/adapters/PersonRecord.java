package com.example.reservationsystem.adapters;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "person")
public class PersonRecord {
    @Id
    private String nationalId;
    private String name;
    private Integer age;
    private String emailAddress;
    private String mobileNumber;
}
