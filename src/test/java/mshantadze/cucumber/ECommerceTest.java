package mshantadze.cucumber;

import com.qaprosoft.carina.core.foundation.AbstractTest;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ECommerceTest.class);

    @Test
    public void doSomething() {
        final User USER = UsersDAO.getUser("standard_user");
        LOGGER.info(Long.toString(USER.getId()));
        final List<Product> PRODUCTS = ProductsDAO.getProducts(USER.getId());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.typeUsername(USER.getUsername());
        loginPage.typePassword(USER.getPassword());
        HomePage homePage = loginPage.submitLoginForm();
        homePage.addProductToCart(PRODUCTS);
        Assert.assertEquals(PRODUCTS.size(), homePage.getProductsCount(), "Cart label doesn't contain correct amount of products.");
        CartPage cartPage = homePage.clickCartButton();
        Map<Long, Product> productMap = cartPage.getPurchasedProductsList().getProductMap();
        Assert.assertEquals(productMap.size(), PRODUCTS.size(), "Cart doesn't contain correct amount of products.");
        for (Product product : PRODUCTS) {
            LOGGER.info("DB Id: " + product.getId());
            LOGGER.info("DB Title: " + product.getTitle());
            LOGGER.info("DB Description: " + product.getDescription());
            LOGGER.info("DB Price: " + product.getPrice());
            Assert.assertEquals(productMap.get(product.getId()), product, "Cart should contain added items " + product.getId());
        }
        final String firstName = "John";
        final String lastName = "Doe";
        final String postalCode = "600000";
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.typeFirstName(firstName);
        checkoutPage.typeLastName(lastName);
        checkoutPage.typePostalCode(postalCode);
        checkoutPage.clickContinueBtn();
        Map<Long, Product> stepTwoProductMap = checkoutPage.getPurchasedProductsList().getProductMap();
        for (Product product : PRODUCTS) {
            LOGGER.info("DB Id: " + product.getId());
            LOGGER.info("DB Title: " + product.getTitle());
            LOGGER.info("DB Description: " + product.getDescription());
            LOGGER.info("DB Price: " + product.getPrice());
            Assert.assertEquals(stepTwoProductMap.get(product.getId()), product, "Cart should contain added items " + product.getId());
        }
        BigDecimal productsTotalPrice = ProductsDAO.getProductsTotalPrice(USER.getId());
        BigDecimal checkoutPageSubtotal = checkoutPage.getSubtotalPrice();

        LOGGER.info("DB Products total: " + productsTotalPrice);
        LOGGER.info("Checkout subtotal: " + checkoutPageSubtotal.toString());

        Assert.assertEquals(productsTotalPrice, checkoutPageSubtotal, "Checkout subtotal price is not correct");
    }
}
