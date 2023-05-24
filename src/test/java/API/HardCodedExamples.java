package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HardCodedExamples {

    //the baseURI is implemented on a class level, we are setting the value of
    //the static variable from the RestAssured class, String baseURI is initialising it
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ4ODc4NjcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NDkzMTA2NywidXNlcklkIjoiNTI5NSJ9.bHi4KRUNqTnxS9C4vFuwXZk0Tz8H25PL4k81VomdAOw";

    //this annotation is needed to run the method because Cucumber
    //relies on Junit to run the test cases
    @Test
    public void createEmployee(){
        //1. prepare the request
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "    \"emp_firstname\": \"Bob\",\n" +
                        "    \"emp_lastname\": \"Brown\",\n" +
                        "    \"emp_middle_name\": \"Bobby\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2000-02-20\",\n" +
                        "    \"emp_status\": \"Confirmed\",\n" +
                        "    \"emp_job_title\": \"Engineer\"\n" +
                        "}");

        //2. hitting the endpoint
        Response response = preparedRequest.when().post("/createEmployee.php");

        //3. verifying the assertions
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Bob")); // using hamcrest matchers
        System.out.println("My testcase is passed");
        //this will print the response in the console
        response.prettyPrint();
    }
}
