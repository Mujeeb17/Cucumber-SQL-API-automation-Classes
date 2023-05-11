package utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));

        initialisePageObjects(); // this will initialise the pages we have in out PageInitialiser class when we open browser

        DOMConfigurator.configure("log4j.xml");
        Log.startTestcase("This is the beginning of my Test case");
        Log.info("my test case is executing right now");
        Log.warning("warning warning this is a warning");
    }

    public static void closeBrowser() {
        Log.info("This test case is about to finish");
        Log.endTestcase("This test case is finished");
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

    // Screenshots method
    public static byte[] takeScreenshot(String imageName){
        //it will get its implementation from RemoteWebDriver class
        TakesScreenshot ts = (TakesScreenshot) driver;

        //this captures the screenshot and stores it as a byte array.
        //the method getScreenshotAs() is returning bytes
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        //this captures the screenshot and stores it in sourceFile variable
        //the same method is now returning a file (the actual picture)
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            //this uses the commons-io dependency
            //this copys the screenshot file and stores it in a new file under our screenshots folder
            //the name is given in the parameters
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILE_PATH + imageName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return picBytes;
    }

    //this method is used for ss method to get timestamp
    public static String getTimeStamp(String pattern){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
