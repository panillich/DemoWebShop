package com.demowebshop.fw;

import com.demowebshop.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemHelper extends BaseHelper {

    private final WebDriverWait wait;

    public ItemHelper(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openHomePage() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    public int getItemsCount() {
        return driver.findElements(By.cssSelector(".product-item")).size();
    }

    public String getItemNameByIndex(int index) {
        List<WebElement> productItems = driver.findElements(By.cssSelector(".product-item"));
        return productItems.get(index).findElement(By.cssSelector(".product-title a")).getText();
    }

    public void addItemToCartByIndex(int index) {
        List<WebElement> productItems = driver.findElements(By.cssSelector(".product-item"));
        productItems.get(index).findElement(By.cssSelector("input[value='Add to cart']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.close")));
        click(By.cssSelector("span.close"));
    }

    public void openCart() {
        click(By.cssSelector(".ico-cart"));
    }

    public boolean isItemInCart(String itemName) {
        return isElementPresent(By.xpath("//td[@class='product']//a[normalize-space(text())='" + itemName + "']"));
    }
    public void clearCart() {
        click(By.cssSelector(".ico-cart"));

        List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart"));

        if (!removeCheckboxes.isEmpty()) {
            for (WebElement checkbox : removeCheckboxes) {
                checkbox.click();
            }
            click(By.name("updatecart"));
        }
    }
}