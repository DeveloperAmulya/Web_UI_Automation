package com.tajhotels.automation.base;

import com.tajhotels.automation.driver.BrowserFactory;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String testngBrowser) {

        // 1) Create driver using BrowserFactory
        WebDriver driver = BrowserFactory.createDriver(testngBrowser);

        // 2) Store driver centrally using DriverFactory
        DriverFactory.setDriver(driver);

        // 3) Validate driver is stored
        Assert.assertNotNull(
                DriverFactory.getDriver(),
                "Driver is NULL after setup"
        );

        // 4) Launch application
        String baseUrl = ConfigReader.get("baseUrl");
        DriverFactory.getDriver().get(baseUrl);

        // 5) Optional validation that app is launched
        Assert.assertTrue(
                DriverFactory.getDriver().getCurrentUrl().contains("taj"),
                "Application URL not loaded correctly"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // 6) Quit browser if exists
        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }

        // 7) Clear ThreadLocal
        DriverFactory.unload();

        // 8) Verify cleanup
        Assert.assertNull(
                DriverFactory.getDriver(),
                "Driver not cleaned up properly after test"
        );
    }
}