package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void testHomePageLoadsAndContainsItems() {
        app.getItem().openHomePage();

        // 1. Проверяем, что на главной странице есть хотя бы 1 товар
        Assert.assertTrue(app.getItem().getItemsCount() > 0,
                "No products found on the Home Page!");

        // 2. Проверяем, что отображается логотип (можно добавить этот локатор в BaseHelper или проверять напрямую)
        Assert.assertTrue(app.getItem().isElementPresent(org.openqa.selenium.By.cssSelector(".header-logo")),
                "Header logo is missing on the Home Page!");

        // 3. Проверяем, что отображается меню категорий (левый блок)
        Assert.assertTrue(app.getItem().isElementPresent(org.openqa.selenium.By.cssSelector(".block-category-navigation")),
                "Category navigation block is missing!");
    }
}
