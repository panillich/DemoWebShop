package core.fw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    private WebDriver driver;
    private UserHelper userHelper;
    private ItemHelper itemHelper;

    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        userHelper = new UserHelper(driver);
        itemHelper = new ItemHelper(driver);
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public UserHelper getUser() {
        return userHelper;
    }

    public ItemHelper getItem() {
        return itemHelper;
    }

    // Оставим геттер для драйвера на случай старых тестов (например DemoWebShopTest)
    public WebDriver getDriver() {
        return driver;
    }
}
