package tests;


import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("valid_username"),
                ConfigReader.getProperty("valid_password")
        );

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "Valid login failed. Inventory page not displayed."
        );

        Assert.assertTrue(
                driver.getTitle().contains("Swag Labs"),
                "Application title is incorrect."
        );

        System.out.println("PASS: Valid login successful");
    }

    @Test(priority = 2)
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("invalid_username"),
                ConfigReader.getProperty("invalid_password")
        );

        String actualMessage =
                loginPage.getErrorMessage();

        Assert.assertTrue(
                actualMessage.contains(
                        "Username and password do not match"),
                "Expected login error message was not displayed."
        );

        System.out.println("PASS: Invalid login validation successful");
    }

    @Test(priority = 3)
    public void emptyLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "");

        String actualMessage =
                loginPage.getErrorMessage();

        Assert.assertTrue(
                actualMessage.contains("Username is required"),
                "Empty username validation failed."
        );

        System.out.println("PASS: Empty login validation successful");
    }
}
