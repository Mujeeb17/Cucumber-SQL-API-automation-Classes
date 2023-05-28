package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// dry run = true-> it will check which step of your feature file does not have glue code

@CucumberOptions(
        features = "src\\test\\resources\\Features\\APIWorkflow.feature",
        glue = "APIStepDefinitions",
        dryRun = false,
        tags = "@update",
        plugin = {"pretty", "html:target/Cucumber.html", "json:target/Cucumber.json", "rerun:target/failed.txt"}
)

public class APIRunner {
}
