package com.automation.highrewards.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseMobileDriver {
    protected static ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private final String platform;
    private final String deviceName;
    private final String appPackage;
    private final String appActivity;
    private final String appPath;
    private final String udid;
    private final String url;

    public BaseMobileDriver(String platform, String deviceName, String appPackage, String appActivity, String appPath, String udid, String url) {
        this.platform = platform;
        this.deviceName = deviceName;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.appPath = appPath;
        this.udid = udid;
        this.url = url;
    }

    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("deviceName", deviceName);

        if (platform.equalsIgnoreCase("Android")) {
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability("udid", udid);
            if (appPath != null) {
                capabilities.setCapability("app", appPath);
            }
            driverThreadLocal.set(new AndroidDriver(new URL(url), capabilities));
        } else if (platform.equalsIgnoreCase("iOS")) {
            capabilities.setCapability("bundleId", appPackage);
            capabilities.setCapability("udid", udid);
            if (appPath != null) {
                capabilities.setCapability("app", appPath);
            }
            driverThreadLocal.set(new IOSDriver(new URL(url), capabilities));
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    public void tearDown() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
        }
        driverThreadLocal.remove();
    }

    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void clearDriver() {
        driverThreadLocal.remove();
    }
}
