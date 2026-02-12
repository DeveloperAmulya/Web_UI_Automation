package com.tajhotels.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.reports.ExtentManager;
import com.tajhotels.automation.utils.ConfigReader;
import com.tajhotels.automation.utils.RunContext;
import com.tajhotels.automation.utils.ScreenshotUtils;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        RunContext.runBase(); // create folders

        ExtentManager.getExtent().setSystemInfo("Browser", ConfigReader.get("browser"));
        ExtentManager.getExtent().setSystemInfo("Env", System.getProperty("env", "default"));
        ExtentManager.getExtent().setSystemInfo("BaseUrl", ConfigReader.get("baseUrl"));
        ExtentManager.getExtent().setSystemInfo("RunFolder", RunContext.runBase().toString());
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.getExtent().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = ExtentManager.getExtent().createTest(testName);
        tlTest.set(test);
        tlTest.get().log(Status.INFO, "Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tlTest.get().log(Status.PASS, "Test Passed");
        tlTest.remove();
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
                tlTest.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception ignored) {}
        }

        tlTest.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        tlTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            tlTest.get().skip(result.getThrowable());
        }
        tlTest.remove();
    }
}