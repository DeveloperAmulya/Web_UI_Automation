package com.tajhotels.automation.tests;

import com.tajhotels.automation.base.BaseTest;
import com.tajhotels.automation.driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTestDemo extends BaseTest {

    @Test
    public void verifyBaseTestSetupAndTeardown() {

        // Driver should already be initialized
        Assert.assertNotNull(
                DriverFactory.getDriver(),
                "Driver is NULL inside test"
        );

        // Base URL should already be opened
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("taj"),
                "Base URL not launched by BaseTest"
        );
    }
}