package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    private ProductsPage loginAndOpenProducts() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("valid_username"),
                ConfigReader.getProperty("valid_password")
        );

        return new ProductsPage(driver);
    }

    @Test
    public void verifyProductListingTest() {

        ProductsPage products =
                loginAndOpenProducts();

        Assert.assertEquals(
                products.getPageTitle(),
                "Products"
        );

        Assert.assertEquals(
                products.getProductCount(),
                6,
                "Expected six products."
        );

        System.out.println(
                "PASS: Product listing verified");
    }

    @Test
    public void verifyProductSortingTest() {

        ProductsPage products =
                loginAndOpenProducts();

        products.sortProducts("lohi");

        Assert.assertEquals(
                products.getFirstProductName(),
                "Sauce Labs Onesie"
        );

        System.out.println(
                "PASS: Products sorted by price low to high");
    }

    @Test
    public void verifyAddProductToCartTest() {

        ProductsPage products =
                loginAndOpenProducts();

        products.addProductToCart(
                "Sauce Labs Backpack");

        Assert.assertEquals(
                products.getCartCount(),
                1
        );

        System.out.println(
                "PASS: Product added to cart");
    }
}

