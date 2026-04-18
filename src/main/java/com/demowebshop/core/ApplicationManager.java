package com.demowebshop.core;

import com.demowebshop.fw.ItemHelper;
import com.demowebshop.fw.UserHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class ApplicationManager {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
        } else {
            driver.set(new ChromeDriver());
        }
        driver.get().manage().window().maximize();
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        File screen = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screen.getAbsolutePath();
    }

    public void stop() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public UserHelper getUser() { return new UserHelper(driver.get()); }
    public ItemHelper getItem() { return new ItemHelper(driver.get()); }
    public WebDriver getDriver() {
        return driver.get();
    }

}