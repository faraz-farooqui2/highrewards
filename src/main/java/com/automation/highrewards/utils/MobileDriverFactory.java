package com.automation.highrewards.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverFactory {

    public static AppiumDriver createDriver(String platform, String deviceName, String appPackage, String appActivity, String appPath, String udid, String url) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("deviceName", deviceName);

        AppiumDriver driver;
        if (platform.equalsIgnoreCase("Android")) {
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability("udid", udid);
            if (appPath != null) {
                capabilities.setCapability("app", appPath);
            }
            driver = new AndroidDriver(new URL(url), capabilities);
        } else if (platform.equalsIgnoreCase("iOS")) {
            capabilities.setCapability("bundleId", appPackage);
            capabilities.setCapability("udid", udid);
            if (appPath != null) {
                capabilities.setCapability("app", appPath);
            }
            driver = new IOSDriver(new URL(url), capabilities);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
        return driver;
    }
}
