package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactPage extends TestBase{
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contacts;
	
	@FindBy(id="first_name")
	WebElement firstname;
	
	@FindBy(id="surname")
	WebElement lastname;
	
	@FindBy(name="client_lookup")
	WebElement companyname;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement save;
	
	public ContactPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactlabel()
	{
		return contacts.isDisplayed();
	}
	
	//Why we written xpath inside action method is sometimes name of the customer changes in that case this xpath will gets failed.
	//So taking name from parameter.
	public void selectContacts(String name)
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]"
				+ "//parent::td"
				+ "//preceding-sibling::td"
				+ "//input[@name='contact_id']")).click();;
	}
	
	public void createNewContact(String title, String fname, String lname, String company)
	{
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByValue(title);
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		companyname.sendKeys(company);
	}
	
	

}
