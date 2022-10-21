package bdd;

import com.example.reservationsystem.usecases.PersonInputBoundary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StepDefinitions {

    @Given("user who does not exist in the system")
    public void userWhoDoesNotExistInTheSystem() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8080", new Object(), Object.class);
    }

    @When("user signs up to the system")
    public void userSignsUpToTheSystem() {

    }

    @Then("user created in the system")
    public void userCreatedInTheSystem() {

    }
}
