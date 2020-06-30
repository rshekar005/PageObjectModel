package com.crm.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long page_load_timeout=20;
	public static long implicit_wait=20;
	public static String TESTDATA_SHEET_PATH;
	
	static Workbook book;
	static Sheet sheet;
	
	public void switchToFrame(String name)
	{
		driver.switchTo().frame(name);
	}
	
	
	public void mouseHover(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();	
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	public String screenshot(String screenshotname,WebDriver driver)
	{
		String destination=null;
	    File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		destination=System.getProperty("user.dir")+"/Screenshot/"+screenshotname+dateandtime()+".png";
	        FileHandler.copy(srcFile, new File(destination));
			//FileUtils.copyFile(srcFile, new File(destination));
			System.out.println("Screenshot Taken");
		} catch (IOException e) {
			System.out.println("Screenshot Failed");
			e.printStackTrace();
			
		}
		return destination;
	}
	public static String dateandtime()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");  
	    Date date = new Date();  
	    String dateandtime= formatter.format(date);
	    System.out.println(dateandtime);
		return dateandtime;
	}

}
