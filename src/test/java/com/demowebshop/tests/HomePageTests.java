package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void testHomePageLoadsAndContainsItems() {
        app.getItem().openHomePage();

        Assert.assertTrue(app.getItem().getItemsCount() > 0,
                "No products found on the Home Page!");

        Assert.assertTrue(app.getItem().isElementPresent(org.openqa.selenium.By.cssSelector(".header-logo")),
                "Header logo is missing on the Home Page!");

        Assert.assertTrue(app.getItem().isElementPresent(org.openqa.selenium.By.cssSelector(".block-category-navigation")),
                "Category navigation block is missing!");
    }
}
