package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import utils.CommonMethods;
import utils.ConfigReader;


public class c1c2Login extends CommonMethods{

//    @Given("open the browser and launch HRMS application")
//    public void open_the_browser_and_launch_hrms_application() {
//
//        openBrowserAndApplication();
//
//    }


    @When("user enters valid email and valid password")
    public void user_enters_valid_email_and_valid_password() {

        driver.findElement(By.id("txtUsername")).sendKeys(ConfigReader.getPropertyValue("un"));
        driver.findElement(By.id("txtPassword")).sendKeys(ConfigReader.getPropertyValue("pw"));

    }

    @When("click on login button")
    public void click_on_login_button() {
        driver.findElement(By.id("btnLogin")).click();
    }

    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {
        boolean userloggedIn = driver.findElement(By.xpath("//a[contains(text(), 'Welcome')]")).isDisplayed();
        if (userloggedIn) {
            System.out.println("User is logged in Successfully");
        }
    }

    @When("user enters valid {string} and valid {string}")
    public void user_enters_valid_and_valid(String username, String password) {
        //the values of username and password are automatically set to the
        //values from the feature file
        sendText(driver.findElement(By.id("txtUsername")), username);
        sendText(driver.findElement(By.id("txtPassword")), password);
    }
}

//    @Then("close the browser")
//    public void close_the_browser() {
//        closeBrowser();
//    }
//}