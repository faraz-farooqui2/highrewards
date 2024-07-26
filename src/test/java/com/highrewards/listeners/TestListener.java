package com.highrewards.listeners;

import com.automation.highrewards.utils.ConfigReader;
import com.automation.highrewards.utils.ScreenshotUtility;
import com.automation.highrewards.base.BaseWebDriver;
import com.automation.highrewards.base.BaseMobileDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener, ISuiteListener {

    private ExtentReports extent;
    private String executionMode;
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        executionMode = ConfigReader.getProperty("executionMode");
        String testType = suite.getName().contains("Mobile") ? "Mobile" : "Web";

        // Create a timestamp for the report folder
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/src/test/resources/extent-reports/" + testType + "/Report_" + timestamp;

        File reportDir = new File(reportPath);
        try {
            if (!reportDir.exists()) {
                boolean created = reportDir.mkdirs();
                if (created) {
                    System.out.println("Directory created successfully: " + reportPath);
                } else {
                    System.out.println("Failed to create directory: " + reportPath);
                }
            } else {
                System.out.println("Directory already exists: " + reportPath);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("Permission denied to create directory: " + reportPath);
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath + "/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        System.out.println("Test suite started: " + suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        if ("sequential".equalsIgnoreCase(executionMode)) {
            ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
            test.set(extentTest);
            System.out.println("Starting test: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success
        if ("sequential".equalsIgnoreCase(executionMode)) {
            test.get().pass("Test passed");
        }
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver webDriver = BaseWebDriver.getDriver();
        AppiumDriver appiumDriver = BaseMobileDriver.getDriver();

        // Capture screenshot on test failure
        if (webDriver != null) {
            try {
                String screenshotPath = ScreenshotUtility.captureWebScreenshot(webDriver, result.getMethod().getMethodName());
                if ("sequential".equalsIgnoreCase(executionMode)) {
                    test.get().fail("Screenshot: " + test.get().addScreenCaptureFromPath(screenshotPath));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (appiumDriver != null) {
            try {
                String screenshotPath = ScreenshotUtility.captureMobileScreenshot(appiumDriver, result.getMethod().getMethodName());
                if ("sequential".equalsIgnoreCase(executionMode)) {
                    test.get().fail("Screenshot: " + test.get().addScreenCaptureFromPath(screenshotPath));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Log test failure details
        if ("sequential".equalsIgnoreCase(executionMode)) {
            test.get().fail(result.getThrowable());
        }
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        System.out.println("Failure reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped details
        if ("sequential".equalsIgnoreCase(executionMode)) {
            test.get().skip(result.getThrowable());
        }
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
        System.out.println("Skip reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optionally handle cases where tests fail but within success percentage
        System.out.println("Test failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Test suite finished: " + context.getName());
        BaseWebDriver.clearDriver();
        BaseMobileDriver.clearDriver();
    }
}
