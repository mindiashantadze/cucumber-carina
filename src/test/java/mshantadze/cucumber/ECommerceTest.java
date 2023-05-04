package mshantadze.cucumber;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import io.cucumber.testng.CucumberOptions;
import mshantadze.cucumber.pages.CartPage;
import mshantadze.cucumber.pages.CheckoutPage;
import mshantadze.cucumber.pages.HomePage;
import mshantadze.cucumber.pages.LoginPage;
import mshantadze.cucumber.utils.db.dao.ProductsDAO;
import mshantadze.cucumber.utils.db.dao.UsersDAO;
import mshantadze.cucumber.utils.db.models.Product;
import mshantadze.cucumber.utils.db.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@CucumberOptions(
        features = "src/test/resources/features/ECommerce.feature",
        glue = "mshantadze.cucumber.stepdefinitions",
        plugin = {"pretty",
                "html:target/cucumber-core-test-report",
                "pretty:target/cucumber-core-test-report.txt",
                "json:target/cucumber-core-test-report.json",
                "junit:target/cucumber-core-test-report.xml"}
)
public class ECommerceTest extends CucumberBaseTest {
}
