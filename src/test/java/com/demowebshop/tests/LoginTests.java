package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import com.demowebshop.data.UserData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void testPositiveLogin() {
        app.getUser().login(UserData.EMAIL, UserData.PASSWORD);

        Assert.assertTrue(app.getUser().isUserLoggedIn(),
                "User is not logged in after valid credentials!");
    }

    // Это и есть Data-Driven Testing (DDT).
    // Метод возвращает двумерный массив объектов: { {email, password}, {email, password} }
    @DataProvider(name = "invalidLoginData")
    public Object[][] provideInvalidData() {
        return new Object[][]{
                {"invalid_email.com", "SecretPass123!"},  // Неверный формат почты
                {UserData.EMAIL, "WrongPassword!!!"},     // Правильная почта, неверный пароль
                {"", ""}                                  // Пустые поля
        };
    }

    // Подключаем DataProvider к тесту
    @Test(dataProvider = "invalidLoginData")
    public void testNegativeLogin(String email, String password) {
        app.getUser().login(email, password);

        // Проверяем, что пользователь НЕ вошел
        Assert.assertFalse(app.getUser().isUserLoggedIn(),
                "User managed to log in with invalid data: " + email);

        // Проверяем, что появилась красная ошибка авторизации
        Assert.assertTrue(app.getUser().isLoginErrorDisplayed(),
                "Login error message is not displayed!");
    }
}