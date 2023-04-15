package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void preCondition() {
        openBrowserAndApplication();
    }

    //Scenario class holds the complete info of your tests execution in Cucumber framework
    @After
    public void postCondition(Scenario scenario) {
        byte[] pic;
        if (scenario.isFailed()) {
            pic = takeScreenshot("failed/" + scenario.getName());
        } else {
            pic = takeScreenshot("passed/" + scenario.getName());
        }

        //attach the screenshot in my report
        scenario.attach(pic, "image/png", scenario.getName());
        closeBrowser();
    }
}
