package com.example.reservationsystem.usecases;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonErrorMessages {
    public static final String NATIONAL_ID_IS_REQUIRED = "National Id is required";
    public static final String NAME_IS_REQUIRED = "Name is required";
    public static final String INVALID_AGE = "Invalid age: less than 15 years old";
    public static final String EMAIL_ADDRESS_IS_REQUIRED = "Email Address is required";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid Email Address";
    public static final String INVALID_MOBILE_NUMBER = "Invalid Mobile Number";
}
