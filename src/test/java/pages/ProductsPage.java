package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle =
            By.className("title");

    private By cartIcon =
            By.className("shopping_cart_link");

    private By cartBadge =
            By.className("shopping_cart_badge");

    private By sortDropdown =
            By.className("product_sort_container");

    private By productNames =
            By.className("inventory_item_name");

    private By productPrices =
            By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(pageTitle, "Products"));
    }

    public String getPageTitle() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(pageTitle))
                .getText();
    }

    public int getProductCount() {
        return driver.findElements(productNames).size();
    }

    public void sortProducts(String option) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(sortDropdown));

        Select select = new Select(
                driver.findElement(sortDropdown));

        select.selectByValue(option);
    }

    public String getFirstProductName() {
        return driver.findElements(productNames)
                .get(0).getText();
    }

    public String getFirstProductPrice() {
        return driver.findElements(productPrices)
                .get(0).getText();
    }

    public void addProductToCart(String productName) {
        By addButton = By.id("add-to-cart-" + productSlug(productName));
        By removeButton = By.id("remove-" + productSlug(productName));
        int expectedCartCount = getCartCount() + 1;

        wait.until(ExpectedConditions.elementToBeClickable(addButton))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
        waitForCartCount(expectedCartCount);
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.id("remove-" + productSlug(productName));
        By addButton = By.id("add-to-cart-" + productSlug(productName));
        int expectedCartCount = getCartCount() - 1;

        wait.until(ExpectedConditions.elementToBeClickable(removeButton))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
        waitForCartCount(expectedCartCount);
    }

    private String productSlug(String productName) {
        return productName.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");
    }

    private void waitForCartCount(int expectedCount) {
        if (expectedCount == 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        } else {
            wait.until(ExpectedConditions.textToBe(
                    cartBadge, Integer.toString(expectedCount)));
        }
    }

    public int getCartCount() {
        List<org.openqa.selenium.WebElement> badges =
                driver.findElements(cartBadge);

        if (badges.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(badges.get(0).getText());
    }

    public void openCart() {
        wait.until(
                        ExpectedConditions.elementToBeClickable(cartIcon))
                .click();
        wait.until(ExpectedConditions.textToBe(pageTitle, "Your Cart"));
    }
}
