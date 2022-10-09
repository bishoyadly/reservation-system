package com.example.reservationsystem.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String nationalId;
    private String name;
    private Integer age;
    private String emailAddress;
    private String mobileNumber;
}
