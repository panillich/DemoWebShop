package core.fw;

import core.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        driver.get("https://demowebshop.tricentis.com/login");
        type(By.id("Email"), email);
        type(By.id("Password"), password);
        click(By.cssSelector("input.login-button"));
    }

    public void openRegistrationPage() {
        driver.get("https://demowebshop.tricentis.com/");
        click(By.cssSelector(".ico-register"));
    }

    public void fillRegistrationForm(User user) {
        click(By.id("gender-male"));
        type(By.id("FirstName"), user.getFirstName());
        type(By.id("LastName"), user.getLastName());
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
        type(By.id("ConfirmPassword"), user.getPassword());
    }

    public void submitRegistration() {
        click(By.id("register-button"));
    }

    public boolean isRegistrationSuccessful() {
        return isElementPresent(By.cssSelector(".result"));
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector(".ico-logout"));
    }
}

