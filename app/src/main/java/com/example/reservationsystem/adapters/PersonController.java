package com.example.reservationsystem.adapters;

import com.example.reservationsystem.usecases.PersonInputBoundary;
import com.example.reservationsystem.usecases.PersonRequest;
import com.example.reservationsystem.usecases.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonInputBoundary personInputBoundary;

    @Autowired
    public PersonController(PersonInputBoundary personInputBoundary) {
        this.personInputBoundary = personInputBoundary;
    }

    @PostMapping
    ResponseEntity<PersonResponse> savePerson(@RequestBody PersonRequest personRequest) {
        return (ResponseEntity<PersonResponse>) personInputBoundary.savePerson(personRequest);
    }
}
