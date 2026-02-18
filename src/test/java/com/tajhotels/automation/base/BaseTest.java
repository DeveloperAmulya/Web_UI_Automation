package com.tajhotels.automation.base;

import com.tajhotels.automation.driver.BrowserFactory;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional String testngBrowser) {

        // 1) Create WebDriver instance
        WebDriver driver = BrowserFactory.createDriver(testngBrowser);

        // 2) Store driver in ThreadLocal
        DriverFactory.setDriver(driver);

        // 3) Validate driver creation
        Assert.assertNotNull(
                DriverFactory.getDriver(),
                "WebDriver initialization failed"
        );

        // 4) Maximize browser window (as requested)
        try {
            DriverFactory.getDriver().manage().window().maximize();
        } catch (Exception ignored) {}

        // 5) Launch application
        String baseUrl = ConfigReader.get("baseUrl");
        DriverFactory.getDriver().get(baseUrl);

        // 6) Light sanity check (do NOT hardcode exact URL)
        Assert.assertTrue(
                DriverFactory.getDriver().getCurrentUrl().toLowerCase().contains("taj"),
                "Application URL not loaded correctly"
        );
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        // 7) Quit browser safely
        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }

        // 8) Clear ThreadLocal reference
        DriverFactory.unload();

        // 9) Final validation of cleanup
        Assert.assertNull(
                DriverFactory.getDriver(),
                "WebDriver was not cleaned up properly"
        );
    }
}