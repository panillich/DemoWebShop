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