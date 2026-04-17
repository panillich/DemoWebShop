package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import com.demowebshop.model.Gender;
import com.demowebshop.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
    public void testCreatePositiveAccount() {
        String uniqueEmail = "panillich" + System.currentTimeMillis() + "@gmail.com";
        User user = new User(Gender.MALE, "Vladimir", "Panchenko", uniqueEmail, "SecretPass123!");

        app.getUser().openRegistrationPage();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitRegistration();

        Assert.assertTrue(app.getUser().isRegistrationSuccessful(),
                "Successful registration message not found!");
        Assert.assertTrue(app.getUser().isUserLoggedIn(),
                "User is not authorized!");
    }
}