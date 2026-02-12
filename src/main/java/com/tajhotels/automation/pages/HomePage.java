package com.tajhotels.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	private WebDriver driver;
	
	// ==========Locators================//
	
	//logo 
	@FindBy(xpath="img[@alt=\"taj-logo\"]")
	private WebElement logo;
	
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
	
	
	//=============methods==================
	
}
