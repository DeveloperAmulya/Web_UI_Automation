package com.tajhotels.automation.tests;

import com.tajhotels.automation.driver.BrowserFactory;
import com.tajhotels.automation.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverSanityTestDemo {

    @Test
    public void verifyDriverLaunchAndQuit() {

        // 1) Create driver (pass null for now: it will fall back to config / -Dbrowser)
        WebDriver driver = BrowserFactory.createDriver(null);

        // 2) Store driver in DriverFactory
        DriverFactory.setDriver(driver);

        // 3) Verify DriverFactory.getDriver() is NOT null
        Assert.assertNotNull(DriverFactory.getDriver(), "Driver is NULL - DriverFactory not holding it!");

        // Optional: open a simple page to confirm browser works
        DriverFactory.getDriver().get("https://www.google.com");

        // 4) Quit driver + cleanup ThreadLocal
        DriverFactory.getDriver().quit();
        DriverFactory.unload();
    }
}