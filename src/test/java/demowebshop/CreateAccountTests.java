package demowebshop;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
        public void testCreatePositiveAccount() {
            String uniqueEmail = "panillich" + System.currentTimeMillis() + "@gmail.com";
            User user = new User("Vladimir", "Panchenko", uniqueEmail, "SecretPass123!");

            driver.get("https://demowebshop.tricentis.com/");
            click(By.cssSelector(".ico-register"));
            click(By.id("gender-male"));

            type(By.id("FirstName"), user.getFirstName());
            type(By.id("LastName"), user.getLastName());
            type(By.id("Email"), user.getEmail());
            type(By.id("Password"), user.getPassword());
            type(By.id("ConfirmPassword"), user.getPassword());

            click(By.id("register-button"));

        Assert.assertTrue(isElementPresent(By.cssSelector(".result")),
                "Successful registration message not found!");
        Assert.assertTrue(isElementPresent(By.cssSelector(".ico-logout")),
                "User is not authorized!");
    }
}