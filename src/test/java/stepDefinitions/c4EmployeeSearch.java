package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

public class c4EmployeeSearch extends CommonMethods {
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        sendText(driver.findElement(By.id("empsearch_id")), ConfigReader.getPropertyValue("empId"));
    }

    @When("clicks on search button")
    public void clicks_on_search_button() {
        doClick(driver.findElement(By.id("searchBtn")));
    }

    @When("user sees employee info displayed")
    public void user_sees_employee_info_displayed() {
        System.out.println("employee info is displayed");
    }

    @When("user select Job Title")
    public void user_select_job_title() {
        WebElement jobTitleDdl = driver.findElement(By.id("empsearch_job_title"));
        selectByOptions(jobTitleDdl, "Singer");
    }
}
