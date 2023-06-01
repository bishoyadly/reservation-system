package com.example.reservationsystem.userapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String nationalId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String emailAddress;
    private String mobileNumber;
}
