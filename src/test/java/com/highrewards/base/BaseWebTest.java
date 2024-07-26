package com.highrewards.base;

import com.automation.highrewards.base.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.automation.highrewards.utils.ConfigReader;
import com.automation.highrewards.utils.WebDriverFactory;

public class BaseWebTest {
    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("URL");

        // Debugging output
        System.out.println("Configured Browser: " + browser);
        System.out.println("Configured URL: " + url);

        // Initialize WebDriver and open URL
        driver = WebDriverFactory.createDriver(browser);
        BaseWebDriver.setDriver(driver);
        BaseWebDriver.getDriver().get(url);
    }

    @AfterClass
    public void tearDown() {
        BaseWebDriver.clearDriver(); // Clean up WebDriver instance
    }
}
