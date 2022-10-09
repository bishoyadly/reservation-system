package com.example.reservationsystem.usecases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseModel {
    private String nationalId;
    private String name;
    private Integer age;
    private String emailAddress;
    private String mobileNumber;
}