## File: C:\QA_Projects\FirstCucumberProject\.gradle\8.14\gc.properties
```
```

## File: C:\QA_Projects\FirstCucumberProject\.gradle\buildOutputCleanup\cache.properties
```
#Fri Apr 24 12:49:48 CEST 2026
gradle.version=8.14
```

## File: C:\QA_Projects\FirstCucumberProject\.gradle\vcs-1\gc.properties
```
```

## File: C:\QA_Projects\FirstCucumberProject\gradle\wrapper\gradle-wrapper.properties
```
#Fri Apr 24 12:49:34 CEST 2026
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.14-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

## File: C:\QA_Projects\FirstCucumberProject\src\main\java\com\ilcarro\core\BasePage.java
```
package com.ilcarro.core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    // Больше нет слова static!
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

    // Возвращаем boolean, а не кидаем assert внутри
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
```

## File: C:\QA_Projects\FirstCucumberProject\src\main\java\com\ilcarro\core\DriverManager.java
```
package com.ilcarro.core;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
```

## File: C:\QA_Projects\FirstCucumberProject\src\main\java\com\ilcarro\pages\HomePage.java
```
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
```

## File: C:\QA_Projects\FirstCucumberProject\src\main\java\com\ilcarro\pages\LoginPage.java
```
package com.ilcarro.pages;

import com.ilcarro.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}
```

## File: C:\QA_Projects\FirstCucumberProject\src\test\java\com\ilcarro\stepDefinitions\HomePageSteps.java
```
package com.ilcarro.stepDefinitions;

import com.ilcarro.core.DriverManager;
import com.ilcarro.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert; // Используем стандартный Assert из JUnit

public class HomePageSteps {
    // Получаем драйвер из менеджера
    HomePage home = new HomePage(DriverManager.getDriver());

    @Given("User opens ilcarro Homepage")
    public void user_opens_ilcarro_HomePage() {
        DriverManager.getDriver().get("https://icarro-v1.netlify.app/search?page=0&size=10");
    }

    @Then("User verifies HomePage title is displayed")
    public void verifies_HomePage_title() {
        // Ассерт находится на уровне шагов!
        Assert.assertTrue("Title is not displayed on Home Page!", home.isTitleDisplayed());
    }
}
```

## File: C:\QA_Projects\FirstCucumberProject\src\test\java\com\ilcarro\stepDefinitions\Hooks.java
```
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
```

## File: C:\QA_Projects\FirstCucumberProject\src\test\java\com\ilcarro\stepDefinitions\LoginSteps.java
```
package com.ilcarro.stepDefinitions;

import com.ilcarro.core.DriverManager;
import com.ilcarro.pages.HomePage;
import com.ilcarro.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginSteps {
    LoginPage login = new LoginPage(DriverManager.getDriver());

    @And("User clicks on login link")
    public void user_clicks_on_login_link(){
        new HomePage(DriverManager.getDriver()).clickOnLoginLink();
    }

    @And("User enters email {string} and password {string}")
    public void user_enters_credentials(String email, String password){
        login.enterData(email, password);
    }

    @And("User clicks on Yalla button")
    public void user_clicks_on_Yalla_button(){
        login.clickOnYalla();
    }

    @Then("User verifies success message {string} is displayed")
    public void verifies_success_Login(String message){
        Assert.assertTrue("Success message is missing or incorrect!",
                login.isSuccessMessageDisplayed(message));
    }
}
```

## File: C:\QA_Projects\FirstCucumberProject\src\test\java\com\ilcarro\TestRunner.java
```
package com.ilcarro;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com/ilcarro/stepDefinitions",
        plugin = {"pretty","json:build/cucumber-report/cucumber.json"})
public class TestRunner {
}
```

## File: C:\QA_Projects\FirstCucumberProject\.gitignore
```
.gradle
/gradle/

### IntelliJ IDEA ###
*.idea
*.iws
*.iml
*.ipr
out/
settings.gradle
gradlew.bat
\gradlew
gradlew
!**/src/main/**/out/
!**/src/test/**/out/

*.log
*.png
```

