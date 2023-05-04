package mshantadze.cucumber.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import mshantadze.cucumber.components.PurchasedProducts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    @FindBy(className = "title")
    private ExtendedWebElement lblTitle;

    @FindBy(className = "cart_list")
    private PurchasedProducts divPurchasedProductsList;

    @FindBy(id = "checkout")
    private ExtendedWebElement btnCheckout;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(lblTitle);
    }

    public PurchasedProducts getPurchasedProductsList() {
        return this.divPurchasedProductsList;
    }

    public CheckoutPage clickCheckoutButton() {
        this.btnCheckout.click();
        return new CheckoutPage(driver);
    }
}
