import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoWebShopTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void openDemoWebShop() {
        driver.get("https://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        System.out.println("Test passed! Title: " + actualTitle);
    }

    @Test
    public void findTenElements() {
        // Переходим на сайт
        driver.get("https://demowebshop.tricentis.com/");

        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        System.out.println("1. Search box found: " + searchBox.isDisplayed());

        WebElement newsletterEmail = driver.findElement(By.id("newsletter-email"));
        System.out.println("2. Field 'email for newsletter' found: " + newsletterEmail.isDisplayed());

        WebElement subscribeButton = driver.findElement(By.id("newsletter-subscribe-button"));
        System.out.println("3. Button Subscribe found: " + subscribeButton.isDisplayed());

        WebElement registerLink = driver.findElement(By.className("ico-register"));
        System.out.println("4. Link on registration found: " + registerLink.isDisplayed());

        WebElement loginLink = driver.findElement(By.className("ico-login"));
        System.out.println("5. Login link found: " + loginLink.isDisplayed());

        WebElement logo = driver.findElement(By.className("header-logo"));
        System.out.println("6. Logo found: " + logo.isDisplayed());

        WebElement shoppingCart = driver.findElement(By.cssSelector("span.cart-label"));
        System.out.println("7. Card found: " + shoppingCart.isDisplayed());

        WebElement searchButton = driver.findElement(By.cssSelector("input.button-1.search-box-button"));
        System.out.println("8. Search button found: " + searchButton.isDisplayed());

        WebElement booksCategory = driver.findElement(By.xpath("//ul[@class='list']//a[contains(text(), 'Books')]"));
        System.out.println("9. Category found 'Books': " + booksCategory.isDisplayed());

        WebElement aboutUsLink = driver.findElement(By.xpath("//a[contains(text(), 'About us')]"));
        System.out.println("10. Link found 'About us': " + aboutUsLink.isDisplayed());

        Assert.assertTrue(searchBox.isDisplayed(), "Поле поиска не отображается!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
