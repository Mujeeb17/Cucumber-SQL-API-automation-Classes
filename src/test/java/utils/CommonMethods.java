package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import stepDefinitions.PageInitialiser;

import java.time.Duration;
import java.util.List;

public class CommonMethods extends PageInitialiser {

    public static WebDriver driver;

    public static void openBrowserAndApplication() {
        ConfigReader.readProperties();
        switch (ConfigReader.getPropertyValue("browser")) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
        }

        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));

        initialisePageObjects(); // this will initialise the pages we have in out PageInitialiser class when we open browser
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void doClick(WebElement element) {
        element.click();
    }

    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static Select clickOnDropdown(WebElement selectElement) {
        Select select = new Select(selectElement);
        return select;
    }

    public static void selectByValue(WebElement selectElement, String value){
        clickOnDropdown(selectElement).selectByValue(value);
    }
    public static void selectByVisibleText(WebElement selectElement, String value){
        clickOnDropdown(selectElement).selectByVisibleText(value);
    }
    public static void selectByIndex(WebElement selectElement, int index){
        clickOnDropdown(selectElement).selectByIndex(index);
    }
    public static void selectByOptions(WebElement selectElement, String text){
        List<WebElement> options = clickOnDropdown(selectElement).getOptions();
        for(WebElement x: options){
            String ddlOptionText = x.getText();
            if(ddlOptionText.equalsIgnoreCase(text)){
                x.click();
            }
        }
    }
}
