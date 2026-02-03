package com.tajhotels.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmokeTests {

    @Test
    public void openTajHomePage_andVerifyTitle() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.tajhotels.com/");

        String title = driver.getTitle();
        Assert.assertTrue(title != null && !title.isBlank(), "Page title should not be blank");

        driver.quit();
    }
}
