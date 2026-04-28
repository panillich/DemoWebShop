package com.ilcarro.stepDefinitions;

import com.ilcarro.core.DriverManager;
import com.ilcarro.pages.HomePage;
import com.ilcarro.pages.LoginPage;
import io.cucumber.datatable.DataTable;
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
    @And("Login with correct email and wrong password")
    public void enter_wrong_password(DataTable table){
//        login = new LoginPage(driver);
        login.enterWrongPassword(table);
    }

    @Then("User verifies error message is displayed")
    public void verifies_error_Login(){
        login.verifyMessage("Login failed");
    }
}