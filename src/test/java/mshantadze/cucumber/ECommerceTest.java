package mshantadze.cucumber;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ECommerce.feature",
        glue = "mshantadze.cucumber.stepdefinitions",
        plugin = {"pretty",
                "html:target/cucumber-core-test-report.html",
                "pretty:target/cucumber-core-test-report.txt",
                "json:target/cucumber-core-test-report.json",
                "junit:target/cucumber-core-test-report.xml"}
)
public class ECommerceTest extends CucumberBaseTest {
}
