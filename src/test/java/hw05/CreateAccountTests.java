package hw05;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
    public void testCreatePositiveAccount() {

        driver.get("https://demowebshop.tricentis.com/");

        click(By.cssSelector(".ico-register"));
        click(By.id("gender-male")); // Выбираем пол
        type(By.id("FirstName"), "John");
        type(By.id("LastName"), "Doe");

        String uniqueEmail = "johndoe" + System.currentTimeMillis() + "@example.com";
        type(By.id("Email"), uniqueEmail);

        type(By.id("Password"), "SecretPass123!");
        type(By.id("ConfirmPassword"), "SecretPass123!");
        click(By.id("register-button"));

        Assert.assertTrue(isElementPresent(By.cssSelector(".result")),
                "Successful registration message not found on page!");

        Assert.assertTrue(isElementPresent(By.cssSelector(".ico-logout")),
                "The 'Log out' button was not found. The user is not authorized!");
    }
}