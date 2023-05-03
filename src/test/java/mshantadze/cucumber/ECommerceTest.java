package mshantadze.cucumber;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import com.zebrunner.carina.webdriver.IDriverPool;
import io.cucumber.testng.CucumberOptions;
import mshantadze.cucumber.pages.HomePage;
import mshantadze.cucumber.pages.LoginPage;
import mshantadze.cucumber.utils.db.models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*@CucumberOptions(
        features = "src/test/resources/features/ECommerce.feature",
        glue = "mshantadze.cucumber.stepdefinitions",
        plugin = {"pretty",
                "html:target/cucumber-core-test-report",
                "pretty:target/cucumber-core-test-report.txt",
                "json:target/cucumber-core-test-report.json",
                "junit:target/cucumber-core-test-report.xml"}
)*/
public class ECommerceTest extends AbstractTest {
    Product product = new Product() {
        {
            setId(2);
        }
    };

    Product product2 = new Product() {
        {
            setId(1);
        }
    };

    List<Product> products = new ArrayList<>();

    @Test
    public void doSomething() {
        products.add(product);
        products.add(product2);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.submitLoginForm();
        HomePage homePage = new HomePage(getDriver());
        homePage.addProductToCart(products);
        Assert.assertEquals(products.size(), homePage.getProductsCount());
    }
}
