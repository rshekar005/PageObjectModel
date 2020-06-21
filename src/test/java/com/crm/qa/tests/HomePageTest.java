package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactPage contactPage;
	public HomePageTest()
	{
		super();
	}
	
	// Test cases should be run independently.
	//Before each test case -- launch the browser and login
	// @test -- execute the test
	// After each test case -- close the browser
	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage= new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil= new TestUtil();
		contactPage= new ContactPage();
	}
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String title= homePage.validateHomePageTitle();
		Assert.assertEquals(title, "CRMPRO", "Page Title is wrong");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest()
	{
		testutil.switchToFrame("mainpanel");
		boolean b= homePage.verifyCorrectUserName();
		Assert.assertTrue(b);
	}
	
	@Test(priority=3)
	public void verifyContactsLink()
	{
		testutil.switchToFrame("mainpanel");
		contactPage=homePage.clickOnContactClick();
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
