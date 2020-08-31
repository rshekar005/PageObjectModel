package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeSuite;

import com.crm.qa.utils.TestUtil;
import com.crm.qa.utils.WebEventListner;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	
	public static Logger log=Logger.getLogger(TestBase.class);
	
	// WebEventListner it is a class which is defined in qa.utils package for listening webdriver actions. It is implemented with WebDriverEventListener(I)
	public static WebEventListner eventlistener;
	

	public TestBase()
	{
		try
		{
			prop= new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//crm//qa//config//config.properties");
			
			try {
				prop.load(file);
				log.info("============Property loaded==============");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		/*
		 * Event Fring
		Creating an onject of EventFiringWebDriver(C) which accepts WebDriver(I) reference.
		e_driver= new EventFiringWebDriver(driver);
		eventlistener= new WebEventListner();
		e_driver.register(eventlistener);
		driver=e_driver;*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);
		
		String url= prop.getProperty("url");
		driver.get(url);
		log.info("=================Entered URL==================");
	}

}
