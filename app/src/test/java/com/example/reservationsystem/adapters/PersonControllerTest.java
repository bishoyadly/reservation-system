package com.example.reservationsystem.adapters;

import com.example.reservationsystem.PersonModelsBuilder;
import com.example.reservationsystem.usecases.PersonInputBoundary;
import com.example.reservationsystem.usecases.PersonRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonInputBoundary personInputBoundary;

    private String PERSON_API_BASE_URL = "/person";

    @SneakyThrows
    public static String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    @SneakyThrows
    void savePerson_caseInvalidRequest() {
        PersonRequest request = PersonModelsBuilder.buildPersonRequest();
        String requestBody = objectToJson(request);
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post(PERSON_API_BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(personInputBoundary, times(1)).savePerson(any(PersonRequest.class));
    }

}
