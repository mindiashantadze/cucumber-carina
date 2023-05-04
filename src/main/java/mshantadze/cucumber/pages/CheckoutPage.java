package mshantadze.cucumber.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import mshantadze.cucumber.components.PurchasedProducts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class CheckoutPage extends AbstractPage {
    @FindBy(id = "first-name")
    private ExtendedWebElement inptFirstName;

    @FindBy(id = "last-name")
    private ExtendedWebElement inptLastName;

    @FindBy(id = "postal-code")
    private ExtendedWebElement inptPostalCode;

    @FindBy(id = "continue")
    private ExtendedWebElement btnContinue;

    @FindBy(className = "cart_list")
    private PurchasedProducts divPurchasedProductsList;

    @FindBy(className = "summary_subtotal_label")
    private ExtendedWebElement lblSubtotal;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void typeFirstName(String firstName) {
        this.inptFirstName.type(firstName);
    }

    public void typeLastName(String lastName) {
        this.inptLastName.type(lastName);
    }

    public void typePostalCode(String postalCode) {
        this.inptPostalCode.type(postalCode);
    }

    public void clickContinueBtn() {
        this.btnContinue.click();
    }

    public PurchasedProducts getPurchasedProductsList() {
        return this.divPurchasedProductsList;
    }

    public BigDecimal getSubtotalPrice() {
        String subtotalPrice = lblSubtotal.getText().replaceAll("[^\\d.]", "");
        return new BigDecimal(subtotalPrice);
    }
}
