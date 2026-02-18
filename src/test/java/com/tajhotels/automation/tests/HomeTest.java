package com.tajhotels.automation.tests;

import com.tajhotels.automation.assertions.SoftVerify;
import com.tajhotels.automation.base.BaseTest;
import com.tajhotels.automation.driver.DriverFactory;
import com.tajhotels.automation.pages.HomePage;
import com.tajhotels.automation.reports.Report;
import com.tajhotels.automation.utils.ScreenshotUtils;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    SoftVerify soft = new SoftVerify();
    

    @Test
    public void verifyHomePageLoading() {
    	WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        Report.info("Step 1: Verify Home Page is Loading");
        soft.verifyTrue(homePage.homePageLoad(),
                "Home page should be successfully landed");
        ScreenshotUtils.takeScreenshot(driver, "HomePage_Loaded");
    }
    
    @Test
    public void verifyLogoDisplayed() {
    	WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        Report.info("Step 2: Verify Taj logo is displayed");
        soft.verifyTrue(homePage.logoDisplayed(),
                "Taj logo should be visible on home page");
        ScreenshotUtils.takeScreenshot(driver, "Logo_Displayed");

        soft.assertAll();
    }

    @Test
    public void verifyHomePageTitle() {
    	WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        Report.info("Step 3: Verify home page title");
        String actualTitle = homePage.titleHomePage();

        System.out.println("Actual title is: " + actualTitle);
        Report.info("Title fetched: " + actualTitle);

        Assert.assertTrue(actualTitle.toLowerCase().contains("taj"),
                "Home page title should contain 'Taj' | Actual: " + actualTitle);

        ScreenshotUtils.takeScreenshot(driver, "HomePage_Title");
    }
    
    @Test
    public void LogoNavigation() throws TimeoutException {
    	WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
    	Report.info("Step4: Navigation checking by clicking on Logo");
    	homePage.LogoNavigation();
    	ScreenshotUtils.takeScreenshot(driver, "Logo clicking is working and navigated to HomePage");
    }
    
    @Test
    public void HeaderLinksDisplay() throws TimeoutException {
    	WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
    	Report.info("Step5: HeaderLinks checking");
    	homePage.HeaderMenuDisplay();
    	ScreenshotUtils.takeScreenshot(driver, "Logo clicking is working and navigated to HomePage");
    	
    	
    }
}
