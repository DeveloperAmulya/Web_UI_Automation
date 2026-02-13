package com.tajhotels.automation.assertions;

import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.reports.Report;
import com.tajhotels.automation.utils.ScreenshotUtils;
import org.testng.asserts.SoftAssert;

public class SoftVerify extends SoftAssert {

    public void verifyTrue(boolean condition, String message) {
        if (condition) {
            Report.pass(message);
        } else {
            Report.fail(message);
            ScreenshotUtils.takeScreenshot(
                    DriverFactory.getDriver(),
                    "SOFT_ASSERT_FAIL"
            );
        }
        assertTrue(condition, message);
    }
}