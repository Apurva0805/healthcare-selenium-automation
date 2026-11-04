package tests;


import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage loginAndOpenProducts() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("valid_username"),
                ConfigReader.getProperty("valid_password")
        );

        return new ProductsPage(driver);
    }

    @Test
    public void verifyCartProductTest() {

        ProductsPage products =
                loginAndOpenProducts();

        products.addProductToCart("Sauce Labs Backpack");

        products.openCart();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getCartTitle(),
                "Your Cart"
        );

        Assert.assertTrue(
                cart.isProductPresent("Sauce Labs Backpack"),
                "Product not found in cart."
        );

        System.out.println(
                "PASS: Cart product verification");
    }

    @Test
    public void verifyRemoveProductTest() {

        ProductsPage products =
                loginAndOpenProducts();

        products.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(products.getCartCount(), 1);

        products.removeProductFromCart(
                "Sauce Labs Backpack");

        Assert.assertEquals(
                products.getCartCount(),
                0
        );

        System.out.println(
                "PASS: Remove product verification");
    }
}

