package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// dry run = true-> it will check which step of your feature file does not have glue code

@CucumberOptions(
        features = "src\\test\\resources\\Features",
        glue = "stepDefinitions",
        dryRun = false,
        tags = "@testcase2",
        plugin = {"pretty", "html:target/Cucumber.html", "json:target/Cucumber.json"}

)
public class SmokeRunner {

    //target folder is mostly used for storing the test case execution reports generated using Cucumber

}
