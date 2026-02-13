package com.tajhotels.automation.tests;

import com.tajhotels.automation.assertions.SoftVerify;
import com.tajhotels.automation.base.BaseTest;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.pages.HomePage;
import com.tajhotels.automation.reports.Report;
import com.tajhotels.automation.utils.ScreenshotUtils;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void verifyHomePage() {

        HomePage homePage = new HomePage(DriverFactory.getDriver());
        SoftVerify soft = new SoftVerify();

        // ✅ DEBUG FIRST (this will tell what's wrong)
        String url = DriverFactory.getDriver().getCurrentUrl();
        String title = DriverFactory.getDriver().getTitle();

        Report.info("DEBUG - Current URL: " + url);
        Report.info("DEBUG - Current Title: " + title);

        // Take screenshot before assertions
        ScreenshotUtils.takeScreenshot(DriverFactory.getDriver(), "BEFORE_ASSERTS");

        Report.info("Step 1: Verify home page is landed");
        soft.verifyTrue(homePage.homePageLanded(),
                "Home page should be successfully landed");

        Report.info("Step 2: Verify Taj logo is displayed");
        soft.verifyTrue(homePage.logoDisplayed(),
                "Taj logo should be visible on home page");

        Report.info("Step 3: Verify home page title");
        String actualTitle = homePage.titleHomePage();
        Report.info("DEBUG - Title from HomePage.titleHomePage(): " + actualTitle);

        soft.verifyTrue(actualTitle.toLowerCase().contains("taj"),
                "Home page title should contain 'Taj' | Actual: " + actualTitle);

        soft.assertAll();
    }
}