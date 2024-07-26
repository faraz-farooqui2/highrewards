package com.highrewards.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.highrewards.utils.ConfigReader;
import com.automation.highrewards.utils.WebDriverFactory;

public class BaseWebTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("URL");

        // Debugging output
        System.out.println("Configured Browser: " + browser);
        System.out.println("Configured URL: " + url);

        // Initialize WebDriver and open URL
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
