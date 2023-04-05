package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import utils.CommonMethods;
import utils.ConfigReader;

public class c3AddEmployee extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        doClick(driver.findElement(By.id("menu_pim_viewPimModule")));
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
    }
    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {
        ConfigReader.readProperties();
        sendText(driver.findElement(By.xpath("//input[@id='firstName']")), (ConfigReader.getPropertyValue("firstName")));
        driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys(ConfigReader.getPropertyValue("middleName"));
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(ConfigReader.getPropertyValue("lastName"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();
        doClick(driver.findElement(By.xpath("//input[@id='btnSave']")));

    }

}
