package com.demowebshop.tests;

import com.demowebshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getUser().login("panillich@gmail.com", "SecretPass123!");
    }

    @Test
    public void addItemToCartTest() {
        app.getItem().openHomePage();
        Assert.assertTrue(app.getItem().getItemsCount() >= 2,
                "There must be at least 2 products on the main page!");

        String expectedProductName = app.getItem().getItemNameByIndex(1);

        app.getItem().addItemToCartByIndex(1);
        app.getItem().openCart();

        Assert.assertTrue(app.getItem().isItemInCart(expectedProductName),
                "The item '" + expectedProductName + "' not found in the cart!");
    }

    @AfterMethod
    public void cleanUp() {
        try {
            app.getItem().clearCart();
        } catch (Exception e) {
            logger.warn("Could not clear cart after test: " + e.getMessage());
        }
    }
}

