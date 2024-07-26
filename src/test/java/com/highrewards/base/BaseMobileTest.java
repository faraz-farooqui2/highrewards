package com.highrewards.base;

import com.automation.highrewards.utils.MobileDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.highrewards.utils.ConfigReader;

import java.net.MalformedURLException;

public class BaseMobileTest {
    protected AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        String platform = ConfigReader.getProperty("platform");
        String deviceName = ConfigReader.getProperty("deviceName");
        String appPackage = ConfigReader.getProperty("appPackage");
        String appActivity = ConfigReader.getProperty("appActivity");
        String appPath = ConfigReader.getProperty("appPath");
        String udid = ConfigReader.getProperty("udid");
        String appiumServerUrl = ConfigReader.getProperty("appiumServerUrl");
        String appUrl = ConfigReader.getProperty("appURL");

        driver = MobileDriverFactory.createDriver(platform, deviceName, appPackage, appActivity, appPath, udid, appiumServerUrl);

        // Open the app URL if applicable
        if (appUrl != null && !appUrl.isEmpty()) {
            driver.get(appUrl); // Open URL before each test
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
