package com.automation.highrewards.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    private static final String BASE_SCREENSHOTS_DIR = System.getProperty("user.dir") + "/src/test/resources/screenshots/";

    public static String captureWebScreenshot(WebDriver driver, String screenshotName) {
        String webDir = BASE_SCREENSHOTS_DIR + "web/";
        createDirectoryIfNotExists(webDir);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = webDir + "web_" + screenshotName + "_" + getCurrentTime() + ".png";
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dest;
    }

    public static String captureMobileScreenshot(AppiumDriver driver, String screenshotName) {
        String mobileDir = BASE_SCREENSHOTS_DIR + "mobile/";
        createDirectoryIfNotExists(mobileDir);
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = mobileDir + "mobile_" + screenshotName + "_" + getCurrentTime() + ".png";
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dest;
    }

    private static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    private static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        try {
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (created) {
                    System.out.println("Directory created successfully: " + directoryPath);
                } else {
                    System.out.println("Failed to create directory: " + directoryPath);
                }
            } else {
                System.out.println("Directory already exists: " + directoryPath);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("Permission denied to create directory: " + directoryPath);
        }
    }
}
