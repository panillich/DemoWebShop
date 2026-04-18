package com.demowebshop.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app;
    protected Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        app = new ApplicationManager(browser);
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (app != null) {
            app.stop();
        }
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("Start test {} ", method.getName());

        if (app.getDriver() != null) {
            app.getDriver().manage().deleteAllCookies();
            app.getDriver().get("https://demowebshop.tricentis.com/");
        }
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: {} ", result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: {}", result.getMethod().getMethodName());
            if (app != null) {
                String screenshotPath = app.takeScreenshot();
                logger.error("Screenshot path: {}", screenshotPath);
            }
        }
        logger.info("Stop test");
        logger.info("=== === ===");
    }
}