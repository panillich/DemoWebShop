package demowebshop;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        driver.get("https://demowebshop.tricentis.com/");


        SoftAssert softAssert = new SoftAssert();
        String formatLine = "%-39s | %-19s | CSS: %-5b | XPath: %-5b%n";

        WebElement searchBoxId = driver.findElement(By.id("small-searchterms"));
        WebElement searchBoxCss = driver.findElement(By.cssSelector("#small-searchterms"));
        WebElement searchBoxXpath = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        System.out.printf(formatLine, "1. Field 'search box' found:", "By.id: " + searchBoxId.isDisplayed(), searchBoxCss.isDisplayed(), searchBoxXpath.isDisplayed());
        assertAllStrategies(softAssert, "1. Search box", "ID", searchBoxId, searchBoxCss, searchBoxXpath);

        WebElement newsletterEmailId = driver.findElement(By.id("newsletter-email"));
        WebElement newsletterEmailCss = driver.findElement(By.cssSelector("#newsletter-email"));
        WebElement newsletterEmailXpath = driver.findElement(By.xpath("//*[@id='newsletter-email']"));
        System.out.printf(formatLine, "2. Field 'email' found:", "By.id: " + newsletterEmailId.isDisplayed(), newsletterEmailCss.isDisplayed(), newsletterEmailXpath.isDisplayed());
        assertAllStrategies(softAssert, "2. Email field", "ID", newsletterEmailId, newsletterEmailCss, newsletterEmailXpath);

        WebElement subscribeButtonId = driver.findElement(By.id("newsletter-subscribe-button"));
        WebElement subscribeButtonCss = driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
        WebElement subscribeButtonXpath = driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
        System.out.printf(formatLine, "3. Button 'Subscribe' found:", "By.id: " + subscribeButtonId.isDisplayed(), subscribeButtonCss.isDisplayed(), subscribeButtonXpath.isDisplayed());
        assertAllStrategies(softAssert, "3. Subscribe button", "ID", subscribeButtonId, subscribeButtonCss, subscribeButtonXpath);

        WebElement registerLinkClassName = driver.findElement(By.className("ico-register"));
        WebElement registerLinkCss = driver.findElement(By.cssSelector(".ico-register"));
        WebElement registerLinkXpath = driver.findElement(By.xpath("//a[@class='ico-register']"));
        System.out.printf(formatLine, "4. Link 'registration' found:", "By.className: " + registerLinkClassName.isDisplayed(), registerLinkCss.isDisplayed(), registerLinkXpath.isDisplayed());
        assertAllStrategies(softAssert, "4. Register link", "ClassName", registerLinkClassName, registerLinkCss, registerLinkXpath);

        WebElement loginLinkClassName = driver.findElement(By.className("ico-login"));
        WebElement loginLinkCss = driver.findElement(By.cssSelector(".ico-login"));
        WebElement loginLinkXpath = driver.findElement(By.xpath("//a[@class='ico-login']"));
        System.out.printf(formatLine, "5. Link 'login' found:", "By.className: " + loginLinkClassName.isDisplayed(), loginLinkCss.isDisplayed(), loginLinkXpath.isDisplayed());
        assertAllStrategies(softAssert, "5. Login link", "ClassName", loginLinkClassName, loginLinkCss, loginLinkXpath);

        WebElement logoClassName = driver.findElement(By.className("header-logo"));
        WebElement logoCss = driver.findElement(By.cssSelector(".header-logo"));
        WebElement logoXpath = driver.findElement(By.xpath("//*[@class='header-logo']"));
        System.out.printf(formatLine, "6. Field 'logo' found:", "By.className: " + logoClassName.isDisplayed(), logoCss.isDisplayed(), logoXpath.isDisplayed());
        assertAllStrategies(softAssert, "6. Logo", "ClassName", logoClassName, logoCss, logoXpath);

        WebElement shoppingCartClassName = driver.findElement(By.className("cart-label"));
        WebElement shoppingCartCss = driver.findElement(By.cssSelector("span.cart-label"));
        WebElement shoppingCartXpath = driver.findElement(By.xpath("//span[@class='cart-label']"));
        System.out.printf(formatLine, "7. Field 'shopping cart' found:", "By.className: " + shoppingCartClassName.isDisplayed(), shoppingCartCss.isDisplayed(), shoppingCartXpath.isDisplayed());
        assertAllStrategies(softAssert, "7. Shopping cart", "ClassName", shoppingCartClassName, shoppingCartCss, shoppingCartXpath);

        WebElement searchButtonClassName = driver.findElement(By.className("search-box-button"));
        WebElement searchButtonCss = driver.findElement(By.cssSelector("input.button-1.search-box-button"));
        WebElement searchButtonXpath = driver.findElement(By.xpath("//input[contains(@class, 'search-box-button')]"));
        System.out.printf(formatLine, "8. Button 'search' found:", "By.className: " + searchButtonClassName.isDisplayed(), searchButtonCss.isDisplayed(), searchButtonXpath.isDisplayed());
        assertAllStrategies(softAssert, "8. Search button", "ClassName", searchButtonClassName, searchButtonCss, searchButtonXpath);

        WebElement booksCategoryLinkText = driver.findElement(By.linkText("Books"));
        WebElement booksCategoryCss = driver.findElement(By.cssSelector("ul.list a[href='/books']"));
        WebElement booksCategoryXpath = driver.findElement(By.xpath("//ul[@class='list']//a[contains(text(), 'Books')]"));
        System.out.printf(formatLine, "9. Category 'Books' found:", "By.linkText: " + booksCategoryLinkText.isDisplayed(), booksCategoryCss.isDisplayed(), booksCategoryXpath.isDisplayed());
        assertAllStrategies(softAssert, "9. Category Books", "LinkText", booksCategoryLinkText, booksCategoryCss, booksCategoryXpath);

        WebElement aboutUsLinkText = driver.findElement(By.linkText("About us"));
        WebElement aboutUsLinkCss = driver.findElement(By.cssSelector("a[href='/about-us']"));
        WebElement aboutUsLinkXpath = driver.findElement(By.xpath("//a[contains(text(), 'About us')]"));
        System.out.printf(formatLine, "10. Link 'About us' found:", "By.linkText: " + aboutUsLinkText.isDisplayed(), aboutUsLinkCss.isDisplayed(), aboutUsLinkXpath.isDisplayed());
        assertAllStrategies(softAssert, "10. Link About Us", "LinkText", aboutUsLinkText, aboutUsLinkCss, aboutUsLinkXpath);

        softAssert.assertAll();
    }

    private void assertAllStrategies(SoftAssert softAssert, String elementName, String firstStrategyName, WebElement el1, WebElement elCss, WebElement elXpath) {
        softAssert.assertTrue(el1.isDisplayed(), elementName + " -> " + firstStrategyName + " strategy failed");
        softAssert.assertTrue(elCss.isDisplayed(), elementName + " -> CSS strategy failed");
        softAssert.assertTrue(elXpath.isDisplayed(), elementName + " -> XPath strategy failed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
