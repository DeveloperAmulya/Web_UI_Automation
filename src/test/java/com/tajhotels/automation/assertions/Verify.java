package com.tajhotels.automation.assertions;

import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.reports.Report;
import com.tajhotels.automation.utils.ScreenshotUtils;
import org.testng.Assert;

public final class Verify {

    private Verify() {}

    public static void isTrue(boolean condition, String message) {
        if (condition) {
            Report.pass(message);
        } else {
            Report.fail(message);
            ScreenshotUtils.takeScreenshot(
                    DriverFactory.getDriver(),
                    "ASSERT_FAIL"
            );
            Assert.fail(message);
        }
    }
}