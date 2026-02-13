package com.tajhotels.automation.pages;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;          
import org.openqa.selenium.StaleElementReferenceException;  
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	// ==========Locators================//
	
	//logo 
	@FindBy(xpath = "//img[@alt='taj-logo']")
	private static WebElement logo;
	
	//global search icon 
	@FindBy(xpath = "")
	private WebElement globalSearchicon;
	
	//book a stay widget 
	@FindBy(xpath = "")
	private WebElement BookaStayWidget;
	
	
	// more dropdown
	@FindBy(xpath = "//span[text()='MORE']")
	private WebElement moreDropDown;
	
	
	//latest offers text
	@FindBy(xpath = "//span[text()='Latest Offers']")
	private WebElement latestOffersText;
	
	
	//exclusivly for you text
	@FindBy(xpath = "//span[text()='Exclusively']")
	private WebElement ExclusivelyForYouText;
	
	
	//explore more text
	@FindBy(xpath = "//span[text()='Explore']")
	private WebElement ExploreMoreText;

	
	//IHCL on footer text
	@FindBy(xpath = "//img[@alt='ihcl-logo']")
	private WebElement FooterIHCLimg;
	
	// Use a stable element that confirms page is loaded
    @FindBy(tagName = "body")
    private static WebElement pageBody;// (Optional) cookie accept button – may appear and block logo
    private final static By cookieAcceptBtn = By.xpath(
            "//*[self::button or self::a][contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'accept') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'agree')]"
    );

    // ============== CONSTRUCTOR ==============
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // ============== PRIVATE HELPERS ==============

    private static void tryAcceptCookiesIfPresent() {
        try {
            WebElement btn = driver.findElement(cookieAcceptBtn);
            if (btn.isDisplayed()) {
                btn.click();
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Explicit wait until page is usable:
     * 1) body visible
     * 2) title not empty
     * 3) try for logo visible (if cookie blocks, we handle it)
     */
    private static void waitForHomePageToLoad() throws TimeoutException {
        wait.until(ExpectedConditions.visibilityOf(pageBody));

		// Sometimes title is empty for a moment → wait for non-empty title
		wait.until(d -> {
		    String t = d.getTitle();
		    return t != null && !t.trim().isEmpty();
		});

		// Handle cookie overlay if present
		tryAcceptCookiesIfPresent();

		// Wait for logo to be visible (with stale handling)
		wait.until(d -> {
		    try {
		        return logo.isDisplayed();
		    } catch (StaleElementReferenceException | NoSuchElementException e) {
		        return false;
		    }
		});
    }

    // ============== METHODS ==============

    /** Verify Home Page landed successfully */
    public static boolean homePageLanded() {
        try {
            waitForHomePageToLoad();
            String title = driver.getTitle();
            System.out.println(" title when page loaded" + title);
            return title != null && !title.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /** Verify Taj logo is displayed */
    public static boolean logoDisplayed() {
        try {
            waitForHomePageToLoad();
            return logo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Get Home Page title */
    public static String titleHomePage() {
        try {
            waitForHomePageToLoad();
            String title = driver.getTitle();
            System.out.println("title verifying : "+title);
            return title == null ? "" : title.trim();
        } catch (Exception e) {
            return "";
        }
    }

    /** (Optional) Debug helper */
    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return "";
        }
    }
}