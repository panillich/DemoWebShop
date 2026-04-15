package core;

import fw.ItemHelper;
import fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void init() {
        driver.set(new ChromeDriver());

        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void stop() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public UserHelper getUser() {
        return new UserHelper(driver.get());
    }

    public ItemHelper getItem() {
        return new ItemHelper(driver.get());
    }
}