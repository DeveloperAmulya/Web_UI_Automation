package com.tajhotels.automation.tests;

import com.tajhotels.automation.base.BaseTest;
import com.tajhotels.automation.driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FailingDemoTest extends BaseTest {

    @Test
    public void intentionalFailureToVerifyScreenshotAndReport() {
        // Force failure so we can verify screenshot + report attachment
        Assert.assertTrue(
                DriverFactory.getDriver().getTitle().contains("THIS_WILL_FAIL"),
                "Intentional failure to validate report + screenshot"
        );
    }
}