package com.highrewards.base;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.highrewards.utils.ConfigReader;
import com.automation.highrewards.utils.MobileDriverFactory; // Ensure this utility is correctly imported

import java.net.MalformedURLException;

public class BaseMobileTest {
    public AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // Retrieve properties directly using ConfigReader
        String platform = ConfigReader.getProperty("platform");
        String deviceName = ConfigReader.getProperty("deviceName");
        String appPackage = ConfigReader.getProperty("appPackage");
        String appActivity = ConfigReader.getProperty("appActivity");
        String appPath = ConfigReader.getProperty("appPath");
        String udid = ConfigReader.getProperty("udid");
        String appiumServerUrl = ConfigReader.getProperty("appiumServerUrl");

        // Create driver using MobileDriverFactory
        driver = MobileDriverFactory.createDriver(platform, deviceName, appPackage, appActivity, appPath, udid, appiumServerUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
