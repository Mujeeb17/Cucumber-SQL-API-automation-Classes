package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void preCondition(){
        openBrowserAndApplication();
    }
    @After
    public void postCondition(){
        closeBrowser();
    }
}
