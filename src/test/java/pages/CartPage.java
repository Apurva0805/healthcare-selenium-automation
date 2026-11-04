package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartTitle = By.className("title");

    private By cartItems =
            By.className("inventory_item_name");

    private By checkoutButton =
            By.id("checkout");

    private By continueShoppingButton =
            By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    public String getCartTitle() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(cartTitle))
                .getText();
    }

    public boolean isProductPresent(String productName) {

        return driver.findElements(cartItems)
                .stream()
                .anyMatch(item ->
                        item.getText().equals(productName));
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() {
        wait.until(
                        ExpectedConditions.elementToBeClickable(checkoutButton))
                .click();
    }

    public void continueShopping() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueShoppingButton)).click();
    }
}

