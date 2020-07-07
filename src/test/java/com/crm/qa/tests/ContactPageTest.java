package com.crm.qa.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.ContactPageUtils;
import com.crm.qa.utils.TestUtil;

public class ContactPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactPage contactPage;
	public ContactPageTest()
	{
		super();
	}
	
	/*To Test Contact Page need below steps
	 * 
	 * 1. Need to initialize a browser
	 * 2. Login into the app which retruns home Page
	 * 3. Using Home Page we clicking on contacts link. Before clicking on contact switching to frame and then click
	 * 4. After clicking it returns contact page 
	 * 5. with that contactPage object performing actions on contact page
	 * 
	 * 
	 */
	@BeforeMethod
	public void setUp()
	{
		initialization();
		testutil= new TestUtil();
		contactPage= new ContactPage();
		loginPage= new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchToFrame("mainpanel");
		
	}
	
	@Test(priority=1)
	public void verifyContactlable()
	{
		contactPage=homePage.clickOnContactClick();
		boolean b=contactPage.verifyContactlabel();
		Assert.assertTrue(b, "Contact lable is missing in contact page");
		System.out.println("Contact Label displayed ? "+b);
	}
	
	@Test(priority=2)
	public void selectContactText()
	{
		contactPage=homePage.clickOnContactClick();
		contactPage.selectContacts("abc y");
		contactPage.selectContacts("Alex Star");
		System.out.println("Clicked on check boxes");
	}
	
	//ContactPageUtils.getDatafromExcel() method will return data in arraylist.
	//Storing those data in testData reference of ArrayList of objects and iterating one by one.
	@DataProvider
	public Iterator<Object[]> getData() throws IOException
	{
		ArrayList<Object[]> testData= ContactPageUtils.getDatafromExcel();
		return testData.iterator();
		
		
	}
	
	@Test(priority=4 , dataProvider="getData")
	public void validateCreateNewContact(String title, String firsname, String lastname, String company)
	{
		homePage.clickonNewContactLink();
		contactPage.createNewContact(title,firsname,lastname,company);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	

}
