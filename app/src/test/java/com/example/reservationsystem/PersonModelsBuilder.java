package com.example.reservationsystem;

import com.example.reservationsystem.usecases.PersonRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonModelsBuilder {

    public static PersonRequest buildPersonRequest() {
        PersonRequest personRequest = new PersonRequest();
        personRequest.setNationalId("nationalId");
        personRequest.setName("name");
        personRequest.setAge(15);
        personRequest.setEmailAddress("test_email_address@mail.com");
        personRequest.setMobileNumber("12345");
        return personRequest;
    }
}
