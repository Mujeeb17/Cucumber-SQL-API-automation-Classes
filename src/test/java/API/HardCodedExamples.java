package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    //the baseURI is implemented on a class level, we are setting the value of
    //the static variable from the RestAssured class, String baseURI is initialising it
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5Njk5ODEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxMzE4MSwidXNlcklkIjoiNTI5NSJ9.eHxxBa44l76eKXGreDag-Hy5pGE6AwoduBT-s72I6Fg";
    static String empID;

    //this annotation is needed to run the method because Cucumber
    //relies on Junit to run the test cases
    @Test
    public void AcreateEmployee(){
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

        response.then().assertThat().header("Content-Type","application/json"); // verifying response header

        //we are capturing employee id from the response
        empID = response.jsonPath().getString("Employee.employee_id");

        //this will print the response in the console
        response.prettyPrint();
    }

    @Test
    public void BgetCreatedEmployee(){

        //1. prepare the request
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", empID);

        //2. hit the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");

        //3. assertions
        response.then().assertThat().statusCode(200);
        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(empID, tempEmpId); // 2 empId's (1 global, 1 local)

        response.prettyPrint();
    }

    @Test
    public void CupdateEmployee(){
        //1. prepare the request
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "    \"employee_id\": \"" + empID + "\",\n" +
                        "    \"emp_firstname\": \"Bob\",\n" +
                        "    \"emp_lastname\": \"Brown\",\n" +
                        "    \"emp_middle_name\": \"Bobby\",\n" +
                        "    \"emp_gender\": \"F\",\n" +
                        "    \"emp_birthday\": \"2023-05-20\",\n" +
                        "    \"emp_status\": \"confirmed\",\n" +
                        "    \"emp_job_title\": \"API Tester\"\n" +
                        "}");

        //2. hit the endpoint
        Response response = preparedRequest.when().put("/updateEmployee.php");

        //3. assertions
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));

        response.prettyPrint();
    }

    @Test
    public void DgetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", empID);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        //if you want to verify the body of the response, you can do that using hamcrest matchers
    }
}
