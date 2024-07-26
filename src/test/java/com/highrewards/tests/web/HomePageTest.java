package com.highrewards.tests.web;

import com.automation.highrewards.pages.web.page.HomePage;
import com.highrewards.base.BaseWebTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseWebTest {

    private HomePage homePage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp(); // Call the base setup method to initialize WebDriver and open URL
        homePage = new HomePage(); // Instantiate the page object
    }

    @Test
    public void testLoginButton() {
        homePage.clickLoginButton(); // Perform actions using HomePage methods

        // Add assertions as needed
    }

    @AfterClass
    @Override
    public void tearDown() {
        super.tearDown(); // Call the base teardown method to clean up WebDriver instance
    }
}
