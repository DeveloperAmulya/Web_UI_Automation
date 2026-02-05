package com.tajhotels.automation.tests;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmokeTests {

    @Test
    public void printAllMoreMenuLinks() throws InterruptedException {
        // 1️⃣ Setup Chrome driver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Block browser notifications
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        // 2️⃣ Open Taj Hotels home page
        driver.get("https://www.tajhotels.com/en-in");
        driver.manage().window().maximize();
        Thread.sleep(4000);
        
        //	click on more option 
        WebElement more = driver.findElement(By.xpath("//span[contains(text(), 'MORE')]"));
        more.click();
        Thread.sleep(5000);
        
        List<WebElement> moreOPtions = driver.findElements(By.xpath("//ul[@role='menu']//a"));
        System.out.println("links under more options are : ");
        for(WebElement links : moreOPtions) {
        	System.out.println(links.getText());
        }
        
        
        
        driver.quit();
    }
}
