package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	/*If used @cacheLookup every time it will call the element. Suppose this element calling ten times in testing. In this case cachelook up fast the scripts because Webelemtn gets stored in cache. 
	whenever element is necessary then it will call from cache memory
	
	But 1 problem is there, somehow page get refreshed then cache memory gets erased then script checking for element
	which is stored in cache then it will throw StateElementReferenceException.
	
	Use this annotation when there is no change in webelement. It will improve the execution of the script
	*/
	@CacheLookup 
	//Page Factory or Object Repository.
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	public WebElement login;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	public WebElement signup;
	
	@FindBy(xpath="(//img[contains(@class,'img-responsive')])[1]")
	public WebElement crmlogo;
	
	//Initializing Page Objects
	public LoginPage()
	{
		//If using @FindBy means using PageFactory class. driver coming from TestBase class
		PageFactory.initElements(driver, this);//Here this means current class. Instead of "this" we can use LoginPage.class
	}
	
	//Actions
	public String validateloginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo()
	{
		return crmlogo.isDisplayed();
	}
	
	//After Successfull login it will move to home page
	public HomePage login(String un , String ps)
	{
		username.sendKeys(un);
		password.sendKeys(ps);
		login.click();
		
		return new HomePage();
	}
	
	

}
