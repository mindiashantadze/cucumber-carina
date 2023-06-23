package mshantadze.cucumber.pages;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import mshantadze.cucumber.utils.db.models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(className = "title")
    private ExtendedWebElement lblTitle;

    @FindBy(xpath = "//*[@class = 'inventory_item' and .//a[@id = 'item_%s_title_link']]//button")
    private ExtendedWebElement btnAddProductToCart;

    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement lblCartProductCount;

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement btnCartLink;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(lblTitle);
    }

    @Override
    public void open() {
        super.openURL(R.CONFIG.get("url") + "inventory.html");
    }

    public void addProductToCart(Product product) {
        this.btnAddProductToCart.format(Long.toString(product.getId())).click();
    }

    public void addProductToCart(List<Product> products) {
        for (Product product : products) {
            this.btnAddProductToCart.format(Long.toString(product.getId())).click();
        }
    }

    public double getProductsCount() {
        final String productCount = this.lblCartProductCount.getText();
        LOGGER.info("Product count: " + productCount);
        return Integer.parseInt(productCount.trim());
    }

    public CartPage clickCartButton() {
        this.btnCartLink.click();
        return new CartPage(driver);
    }
}
