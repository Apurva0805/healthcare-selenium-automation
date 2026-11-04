package base;

import factory.DriverFactory;
import utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.initDriver();

        driver.manage().window().maximize();

        driver.get(
                ConfigReader.getProperty("url"));

        System.out.println("Browser launched successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        DriverFactory.quitDriver();

        System.out.println("Browser closed successfully");
    }
}

