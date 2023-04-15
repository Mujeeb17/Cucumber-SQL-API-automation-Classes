package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt",
        glue = "stepDefinitions",
        dryRun = false,
        //tags don't make sense here because we want to run test cases from failed.txt file
        plugin = {"pretty"}
)

public class FailedRunner {


}
