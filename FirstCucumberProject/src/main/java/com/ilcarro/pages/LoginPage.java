package com.ilcarro.pages;

import com.ilcarro.core.BasePage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[type='email']")
    WebElement emailInput;
    @FindBy(css = "input[type='password']")
    WebElement passwordInput;

    public LoginPage enterData(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        return this;
    }

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    public LoginPage clickOnYalla() {
        click(loginButton);
        return this;
    }

    @FindBy(css = "h3")
    WebElement successMessage;

    public boolean isSuccessMessageDisplayed(String message) {
        return isTextPresentInElement(successMessage, message, 5);
    }

    public LoginPage verifyMessage(String text) {
        return this;
    }

    public LoginPage enterWrongPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();

        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        enterData(email, password);
        return this;
    }
}