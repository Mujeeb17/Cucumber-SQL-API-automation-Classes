package APIStepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;


public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    public static String empId;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @Given("a request is prepared to create an employee using json payload")
    public void a_request_is_prepared_to_create_an_employee_using_json_payload() {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).
                body(APIPayloadConstants.createEmployeePayloadJson());
    }

    @When("a POST call is made to create a employee")
    public void a_post_call_is_made_to_create_a_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer code) {
        response.prettyPrint();
        response.then().assertThat().statusCode(code);
    }

    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String msg, String value) {
        response.then().assertThat().body(msg, equalTo(value));
    }

    @Then("the employee id {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String string) {
        empId = response.jsonPath().getString("Employee.employee_id");
        System.out.println(empId);
    }

    //****************************************************************************************

    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).
                queryParam("employee_id", empId);
    }

    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        response = request.get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer code) {
        response.then().assertThat().statusCode(code);
    }

    @Then("the employee data we get having id {string} must match with globally stored employee id")
    public void the_employee_data_we_get_having_id_must_match_with_globally_stored_employee_id(String string) {
        //it will store the employee id coming from the get call which will be compared to
        //global employee id
        String tempEmpId = response.jsonPath().getString(string);
        Assert.assertEquals(empId, tempEmpId);
    }

    @Then("the retrieved data at {string} object matches with the data of created employee")
    public void the_retrieved_data_at_object_matches_with_the_data_of_created_employee(String empObject, DataTable dataTable) {
        //String.class is specifying the data you are passing from feature file
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        //getting the data from the response so that we can compare it to the datatable
        Map<String, String> actualData = response.body().jsonPath().get(empObject);

        for (Map<String, String> map : expectedData) {
            //it will store all the keys
            //we don't want duplicate keys so use Set
            Set<String> keys = map.keySet();

            //another for loop to get values and keys
            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

}
