package com.tajhotels.automation.driver;

import com.tajhotels.automation.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserFactory {

    private BrowserFactory(){}

    public static WebDriver createDriver(String testngBrowser) {

        // Priority: Maven > TestNG > config.properties
        String browser = System.getProperty("browser");

        if (browser == null || browser.trim().isEmpty()) {
            browser = (testngBrowser != null && !testngBrowser.trim().isEmpty())
                    ? testngBrowser
                    : ConfigReader.get("browser");
        }

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", ConfigReader.get("headless"))
        );

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new");
                co.addArguments("--disable-notifications");
                return new ChromeDriver(co);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                return new FirefoxDriver(fo);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo = new EdgeOptions();
                if (headless) eo.addArguments("headless");
                return new EdgeDriver(eo);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}