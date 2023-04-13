package Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    //Page Factory Model
    // a better way to maintain web elements
    @FindBy(id = "txtUsername")
    public WebElement usernameTextBox;
    @FindBy(id = "txtPassword")
    public WebElement passwordTextBox;
    @FindBy(id = "btnLogin")
    public WebElement loginBtn;
    @FindBy(id = "welcome")
    public WebElement welcomeIcon;
    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutLink;

    public LoginPage() {

        //this initialises all elements on this page with driver
        PageFactory.initElements(driver, this);
    }
}
