package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	private LoginPage loginPage;
	private HomePage homePage;
	// It will initialize properties of testbase
	public  LoginPageTest() {
		super();  // It will call immediate to parent class constructor. Here it is TestBase
	}
	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage= new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String title=loginPage.validateloginPageTitle();
		Assert.assertEquals(title, 
				"CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest()
	{
		boolean b= loginPage.validateCRMLogo();
		Assert.assertTrue(b);// If b== true then this case pass otherwise fails
	}
	
	@Test(priority=3)
	public void loginTest()
	{
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
