package com.highrewards.base;

import org.openqa.selenium.WebDriver;

import com.automation.highrewards.utils.WebDriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseWebTest {
    public WebDriver driver;
    private String browser = "chrome"; // Set default browser or retrieve from config
    private String url = "http://example.com"; // Set default URL or retrieve from config

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createDriver(browser);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

