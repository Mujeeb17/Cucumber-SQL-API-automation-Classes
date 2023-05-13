package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.DBUtility;
import utils.GlobalVariables;

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
        Assert.assertTrue(addEmpPage.saveBtn.isDisplayed());
        doClick(addEmpPage.saveBtn);
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String fName, String mName, String lName) {
        sendText(addEmpPage.firstNameBox, fName);
        sendText(addEmpPage.middleNameBox, mName);
        sendText(addEmpPage.lastNameBox, lName);
    }
    @When("user captures the employee id")
    public void user_captures_the_employee_id() {
        GlobalVariables.empId = addEmpPage.empIdBox.getAttribute("value");
    }
    @When("query the information in back end")
    public void query_the_information_in_back_end() {

        String query = "select * from hs_hr_employees " +
                       "where employee_id = '" + GlobalVariables.empId + "';";
        //to store the table coming from db, we used a global variable here
        GlobalVariables.tableData = DBUtility.getListOfMapsFromRset(query);
    }
    @Then("verify the results from front end and backend")
    public void verify_the_results_from_front_end_and_backend() {

        // VERIFICATION IS EXISTANCE OF CERTAIN ELEMENTS
        // VALIDATION IS CHECKING OF ELEMENT IS EDITABLE

        String fNameFromDB = GlobalVariables.tableData.get(0).get("emp_firstname");
        System.out.println(fNameFromDB);

        String lNameFromDB = GlobalVariables.tableData.get(0).get("emp_lastname");
        System.out.println(lNameFromDB);

        Assert.assertEquals(fNameFromDB, "nesha");
        Assert.assertEquals(lNameFromDB, "standart");
    }

}
