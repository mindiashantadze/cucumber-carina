package mshantadze.cucumber.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import mshantadze.cucumber.utils.db.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchasedProducts extends AbstractUIObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasedProducts.class);

    @FindBy(className = "cart_item")
    private List<ExtendedWebElement> divCartDetails;

    public PurchasedProducts(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }


    public Map<Long, Product> getProductMap() {
        Map<Long, Product> productMap = new HashMap<>();
        for (ExtendedWebElement divCartDetail : divCartDetails) {
            // in this case xpath was searching for items that are not children of divCartDetail for some reason
            final String idStr = divCartDetail
                    .findExtendedWebElement(By.className("cart_item_label"))
                    .findExtendedWebElement(By.tagName("a"))
                    .getAttribute("id");
            LOGGER.info("Id Str " + idStr);
            final long id = Long.parseLong(idStr.replaceAll("\\D", ""));

            final String title = divCartDetail.findExtendedWebElement(By.className("inventory_item_name")).getText().trim();
            final String description = divCartDetail.findExtendedWebElement(By.className("inventory_item_desc")).getText().trim();

            final String priceStr = divCartDetail.findExtendedWebElement(By.className("inventory_item_price")).getText();
            final double price = Double.parseDouble(priceStr.replace("$", ""));

            LOGGER.info("Id: " + id);
            LOGGER.info("Title: " + title);
            LOGGER.info("Description: " + description);
            LOGGER.info("Price: " + price);

            productMap.put(id, new Product() {
                {
                    setId(id);
                    setTitle(title);
                    setDescription(description);
                    setPrice(price);
                }
            });
        }

        return productMap;
    }
}
