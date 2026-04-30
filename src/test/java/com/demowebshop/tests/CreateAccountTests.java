package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import com.demowebshop.model.Gender;
import com.demowebshop.model.User;
import com.demowebshop.utilits.MyDataProviders;
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

        Assert.assertTrue(app.getUser().isRegistrationSuccessful(), "Successful registration message not found!");
        Assert.assertTrue(app.getUser().isUserLoggedIn(), "User is not authorized!");
    }

    @Test(dataProvider = "negativeRegistrationData", dataProviderClass = MyDataProviders.class)
    public void testCreateAccountNegative(User invalidUser, String expectedErrorMessage) {
        app.getUser().openRegistrationPage();
        app.getUser().fillRegistrationForm(invalidUser);
        app.getUser().submitRegistration();

        Assert.assertFalse(app.getUser().isRegistrationSuccessful(),
                "Registration was successful, but it shouldn't be for user: " + invalidUser.email());

        boolean isErrorPresent = app.getUser().isElementPresent(org.openqa.selenium.By.xpath("//*[contains(text(), '" + expectedErrorMessage + "')]"));
        Assert.assertTrue(isErrorPresent, "Expected error message '" + expectedErrorMessage + "' was not found!");
    }

    @Test
    public void testRegistrationExistingEmail() {
        // Используем email, который 100% уже зарегистрирован
        User user = new User(Gender.MALE, "Vladimir", "Panchenko", "panillich@gmail.com", "SecretPass123!");

        app.getUser().openRegistrationPage();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitRegistration();

        Assert.assertFalse(app.getUser().isRegistrationSuccessful());

        String actualSummaryError = app.getUser().getRegistrationErrorText();
        Assert.assertTrue(actualSummaryError.contains("The specified email already exists"),
                "Не найдено сообщение о дубликате email!");
    }
    @Test
    public void testRegistrationEmptyFirstName() {
        // Создаем юзера без имени
        User user = new User(Gender.MALE, "", "Panchenko", "test12345@gmail.com", "SecretPass123!");

        app.getUser().openRegistrationPage();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitRegistration();

        Assert.assertFalse(app.getUser().isRegistrationSuccessful(), "Регистрация прошла, хотя не должна была!");
        String actualError = app.getUser().getFieldErrorText("FirstName");

        Assert.assertEquals(actualError, "First name is required.", "Текст ошибки под полем FirstName не совпадает!");
    }
}