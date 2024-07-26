package com.highrewards.tests.web;

import com.automation.highrewards.base.BaseWebDriver;
import com.automation.highrewards.utils.ConfigReader;
import com.automation.highrewards.pages.web.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {

    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        BaseWebDriver.setDriver(driver); // Set the driver in BaseWebDriver

        homePage = new HomePage(); // Instantiate the page object
    }

    @Test
    public void testLoginButton() {
        // Retrieve base URL from the config file
        String baseUrl = ConfigReader.getProperty("URL");
        homePage.openUrl(baseUrl); // Open the URL
        homePage.clickLoginButton(); // Perform actions using HomePage methods

        // Add assertions as needed
    }

    @AfterClass
    public void tearDown() {
        BaseWebDriver.clearDriver(); // Clean up WebDriver instance
    }
}
