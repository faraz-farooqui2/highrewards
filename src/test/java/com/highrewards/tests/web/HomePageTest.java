package com.highrewards.tests.web;

import com.automation.highrewards.base.BaseWebDriver;
import com.automation.highrewards.pages.web.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {

    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        BaseWebDriver.setDriver(driver);

        homePage = new HomePage();
    }

    @Test
    public void testLoginButton() {
        homePage.openUrl("http://example.com"); // Use BaseWebDriver method
        homePage.clickLoginButton(); // Perform actions using HomePage methods
        // Add assertions as needed
    }

    @AfterClass
    public void tearDown() {
        BaseWebDriver.clearDriver(); // Clean up WebDriver instance
    }
}
