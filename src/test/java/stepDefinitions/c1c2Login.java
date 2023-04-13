package stepDefinitions;

import Pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;
import java.util.Stack;


public class c1c2Login extends CommonMethods{

//    @Given("open the browser and launch HRMS application")
//    public void open_the_browser_and_launch_hrms_application() {
//
//        openBrowserAndApplication();
//
//    }


    @When("user enters valid email and valid password")
    public void user_enters_valid_email_and_valid_password() {
        login.usernameTextBox.sendKeys(ConfigReader.getPropertyValue("un"));
        login.passwordTextBox.sendKeys(ConfigReader.getPropertyValue("pw"));

    }

    @When("click on login button")
    public void click_on_login_button() {
        login.loginBtn.click();
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
        sendText(login.usernameTextBox, username);
        sendText(login.passwordTextBox, password);
    }

    @When("user enters username and password and verifies login")
    public void user_enters_username_and_password_and_verifies_login(DataTable dataTable) {

        List<Map<String, String>> userCredentials = dataTable.asMaps();

        for(Map<String, String> x: userCredentials){
            String un = x.get("username");
            String pw = x.get("password");

            sendText(login.usernameTextBox, un);
            sendText(login.passwordTextBox, pw);

            doClick(login.loginBtn);

            doClick(login.welcomeIcon);

            //so that the loop will work for the rest of the datan sets
            doClick(login.logoutLink);
        }
    }
}

//    @Then("close the browser")
//    public void close_the_browser() {
//        closeBrowser();
//    }
//}