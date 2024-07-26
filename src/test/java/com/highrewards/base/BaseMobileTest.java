package com.highrewards.base;



import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.highrewards.utils.*;


import java.net.MalformedURLException;

public class BaseMobileTest {
    public AppiumDriver driver;
    private ConfigReader config;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        config = new ConfigReader("config.properties");
        String platform = config.getProperty("platform");
        String deviceName = config.getProperty("deviceName");
        String appPackage = config.getProperty("appPackage");
        String appActivity = config.getProperty("appActivity");
        String appPath = config.getProperty("appPath");
        String udid = config.getProperty("udid");
        String appiumServerUrl = config.getProperty("appiumServerUrl");

        driver = MobileDriverFactory.createDriver(platform, deviceName, appPackage, appActivity, appPath, udid, appiumServerUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
