package com.tajhotels.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.reporting.ExtentManager;
import com.tajhotels.automation.utils.ConfigReader;
import com.tajhotels.automation.utils.ScreenshotUtils;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        // Initialize report once per suite and add execution context
        ExtentManager.getExtent().setSystemInfo("Browser", ConfigReader.get("browser"));
        ExtentManager.getExtent().setSystemInfo("Env", System.getProperty("env", "default"));
        ExtentManager.getExtent().setSystemInfo("BaseUrl", ConfigReader.get("baseUrl"));
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.getExtent().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getExtent().createTest(result.getMethod().getMethodName());
        tlTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tlTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        tlTest.get().log(Status.FAIL, "Test Failed");
        tlTest.get().fail(result.getThrowable());

        String screenshotPath = ScreenshotUtils.takeScreenshot(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName()
        );

        if (screenshotPath != null) {
            try {
                tlTest.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        tlTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            tlTest.get().skip(result.getThrowable());
        }
    }
}