package fw;

import model.Gender;
import model.User;
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
        if (user.gender() == Gender.MALE) {
            click(By.id("gender-male"));
        } else {
            click(By.id("gender-female"));
        }

        type(By.id("FirstName"), user.firstName());
        type(By.id("LastName"), user.lastName());
        type(By.id("Email"), user.email());
        type(By.id("Password"), user.password());
        type(By.id("ConfirmPassword"), user.password());
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

