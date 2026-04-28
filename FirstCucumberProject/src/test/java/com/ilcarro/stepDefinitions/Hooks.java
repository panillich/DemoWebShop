package com.ilcarro.stepDefinitions;

import com.ilcarro.core.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}