package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CreateAnEmployee {

    RequestSpecification createEmployeeRequest;
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        createEmployeeRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", GenerateToken.token).
                body("{\n" +
                        "    \"emp_firstname\": \"Bob\",\n" +
                        "    \"emp_lastname\": \"Brown\",\n" +
                        "    \"emp_middle_name\": \"Bobby\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2000-02-20\",\n" +
                        "    \"emp_status\": \"Confirmed\",\n" +
                        "    \"emp_job_title\": \"Engineer\"\n" +
                        "}");
    }
    @When("a POST call is made to create a employee")
    public void a_post_call_is_made_to_create_a_employee() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
