package mshantadze.cucumber.stepdefinitions;

import com.zebrunner.carina.webdriver.IDriverPool;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mshantadze.cucumber.pages.CartPage;
import mshantadze.cucumber.pages.CheckoutPage;
import mshantadze.cucumber.pages.HomePage;
import mshantadze.cucumber.pages.LoginPage;
import mshantadze.cucumber.utils.db.dao.ProductsDAO;
import mshantadze.cucumber.utils.db.dao.UsersDAO;
import mshantadze.cucumber.utils.db.models.Product;
import mshantadze.cucumber.utils.db.models.User;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ECommerceSteps implements IDriverPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ECommerceSteps.class);
    private User USER;
    private List<Product> PRODUCTS;

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    private static final String ORDER_SUCCESS_MESSAGE = "Thank you for your order!";

    @Given("User opens the website")
    public void user_opens_the_website() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
    }

    @Given("User logs in as {string}")
    public void user_logs_in_as_username(String username) {
        USER = UsersDAO.getUser(username);
        PRODUCTS = ProductsDAO.getProducts(USER.getId());

        loginPage.addCookie(new Cookie("session-username", username));
        homePage = new HomePage(getDriver());
        homePage.open();
    }

    @And("User adds products to the cart")
    public void user_adds_products_to_the_cart() {
        homePage.addProductToCart(PRODUCTS);
    }

    @Then("User should see label on cart icon with product count")
    public void user_should_see_label_on_cart_icon_with_product_count() {
        Assert.assertEquals(PRODUCTS.size(), homePage.getProductsCount(), "Cart label doesn't contain correct amount of products.");
    }

    @And("User goes to the cart page")
    public void user_goes_to_the_cart_page() {
        cartPage = homePage.clickCartButton();
    }

    @And("User goes to the checkout page")
    public void user_goes_to_the_checkout_page() {
        checkoutPage = cartPage.clickCheckoutButton();
    }

    @Then("User should see list of his\\/her products in cart")
    public void user_should_see_list_of_his_her_products_in_cart() {
        Map<Long, Product> productMap = cartPage.getPurchasedProductsList().getProductMap();
        Assert.assertEquals(productMap.size(), PRODUCTS.size(), "Cart doesn't contain correct amount of products.");
        for (Product product : PRODUCTS) {
            LOGGER.info("DB Id: " + product.getId());
            LOGGER.info("DB Title: " + product.getTitle());
            LOGGER.info("DB Description: " + product.getDescription());
            LOGGER.info("DB Price: " + product.getPrice());
            Assert.assertEquals(productMap.get(product.getId()), product, "Cart should contain added items " + product.getId());
        }
    }

    @When("User enters required info")
    public void user_enters_required_info() {
        final String firstName = "John";
        final String lastName = "Doe";
        final String postalCode = "600000";

        checkoutPage.typeFirstName(firstName);
        checkoutPage.typeLastName(lastName);
        checkoutPage.typePostalCode(postalCode);
        checkoutPage.clickContinueBtn();
    }

    @Then("User should see list of his\\/her products in checkout")
    public void user_should_see_list_of_his_her_products_in_checkout() {
        Map<Long, Product> stepTwoProductMap = checkoutPage.getPurchasedProductsList().getProductMap();
        for (Product product : PRODUCTS) {
            LOGGER.info("DB Id: " + product.getId());
            LOGGER.info("DB Title: " + product.getTitle());
            LOGGER.info("DB Description: " + product.getDescription());
            LOGGER.info("DB Price: " + product.getPrice());
            Assert.assertEquals(stepTwoProductMap.get(product.getId()), product, "Cart should contain added items " + product.getId());
        }
    }

    @When("User clicks on finish button")
    public void user_clicks_on_finish_button() {
        checkoutPage.clickFinishBtn();
    }

    @Then("User should see correct subtotal price")
    public void user_should_see_correct_subtotal_price() {
        BigDecimal productsTotalPrice = ProductsDAO.getProductsTotalPrice(USER.getId());
        BigDecimal checkoutPageSubtotal = checkoutPage.getSubtotalPrice();

        LOGGER.info("DB Products total: " + productsTotalPrice);
        LOGGER.info("Checkout subtotal: " + checkoutPageSubtotal.toString());

        Assert.assertEquals(productsTotalPrice, checkoutPageSubtotal, "Checkout subtotal price is not correct");
    }

    @Then("User should see thank you message")
    public void user_should_see_thank_you_message() {
        Assert.assertEquals(checkoutPage.getThankYouMessage(), ORDER_SUCCESS_MESSAGE, "User should get thank you message.");
    }
}
