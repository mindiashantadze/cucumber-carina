package mshantadze.cucumber.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(id = "user-name")
    private ExtendedWebElement inptUsername;

    @FindBy(id = "password")
    private ExtendedWebElement inptPassword;

    @FindBy(id = "login-button")
    private ExtendedWebElement btnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void typeUsername(String username) {
        this.inptUsername.type(username);
    }

    public void typePassword(String password) {
        this.inptPassword.type(password);
    }

    public HomePage submitLoginForm() {
        this.btnLogin.click();
        return new HomePage(driver);
    }
}
