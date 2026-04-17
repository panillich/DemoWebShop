package com.demowebshop.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {

    protected ApplicationManager app;

    protected Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        app = new ApplicationManager(browser);
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("Start test {} ", method.getName());
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if(result.isSuccess()){
            logger.info("PASSED: {} ", result.getMethod().getMethodName());
        }else {
            logger.error("FAILED: {}. Screenshot -> {}", result.getMethod().getMethodName(),
                    app.takeScreenshot());
      }
        logger.info("Stop test");
        logger.info("=== === ===");
    }
}
