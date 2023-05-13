package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimTab;

    @FindBy(xpath = "//a[@id='menu_pim_addEmployee']")
    public WebElement addEmpOption;

    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstNameBox;

    @FindBy(xpath = "//input[@id='middleName']")
    public WebElement middleNameBox;

    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastNameBox;

    @FindBy(xpath = "//input[@id='btnSave']")
    public WebElement saveBtn;
    @FindBy(xpath = "//input[@id='employeeId']")
    public WebElement empIdBox;
}
