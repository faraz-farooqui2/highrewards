package com.automation.highrewards.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWebDriver {
    protected static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected WebDriverWait wait;

    public BaseWebDriver() {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
    }

    public void openUrl(String url) {
        getDriver().get(url);
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void clearDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
        }
        driverThreadLocal.remove();
    }
}
