	package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: Demo User')]")
	WebElement name;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contacts;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement deals;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newcontact;
	
	//Initializing objects
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName()
	{
		return name.isDisplayed();
	}
	
	public ContactPage clickOnContactClick()
	{
		contacts.click();
		return new ContactPage();
	}
	
	public DealsPage clickOnDealsClick()
	{
		deals.click();
		return new DealsPage();
	}
	
	public void clickonNewContactLink()
	{
		Actions action = new Actions(driver);
		action.moveToElement(contacts).build().perform();
		newcontact.click();
	}

}
