package com.ilcarro.pages;

import com.ilcarro.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    WebElement title;

    @FindBy(css = ".navigation-link[href='/login']")
    WebElement loginLink;

    // Метод просто возвращает true/false
    public boolean isTitleDisplayed() {
        return isElementVisible(title);
    }

    public LoginPage clickOnLoginLink() {
        click(loginLink);
        return new LoginPage(driver);
    }
}