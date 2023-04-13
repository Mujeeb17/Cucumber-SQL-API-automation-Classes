package stepDefinitions;

import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigReader;

public class c3AddEmployee extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        doClick(addEmpPage.pimTab);
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        doClick(addEmpPage.addEmpOption);
    }
    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {
        ConfigReader.readProperties();
        sendText(addEmpPage.firstNameBox, (ConfigReader.getPropertyValue("firstName")));
        addEmpPage.middleNameBox.sendKeys(ConfigReader.getPropertyValue("middleName"));
        addEmpPage.lastNameBox.sendKeys(ConfigReader.getPropertyValue("lastName"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        doClick(addEmpPage.saveBtn);

    }

}
