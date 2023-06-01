package com.example.reservationsystem.userapi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "system_user")
public class UserEntity {
    @Id
    private String nationalId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String emailAddress;
    private String mobileNumber;
}
