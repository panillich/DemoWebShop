package com.ilcarro.core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebDriverWait getWait(int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public boolean isTextPresentInElement(WebElement element, String text, int time) {
        try {
            return getWait(time).until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}