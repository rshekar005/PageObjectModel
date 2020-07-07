package com.qa.ExtentReportListener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.base.TestBase;
import com.crm.qa.utils.TestUtil;

public class ExtentReportListener extends TestUtil implements ITestListener{
	
	ExtentHtmlReporter htmlReport;
	ExtentReports report;
	ExtentTest test;


	@BeforeTest
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName());
		System.out.println("Which Test Case Started : " +result.getMethod().getMethodName().toString());
		
	}

	public void onTestSuccess(ITestResult result) {
		if(result.getStatus()==result.SUCCESS)
		{
		test.log(Status.PASS, result.getMethod().getMethodName().toString());
		try {
			test.pass("Test case pass" , MediaEntityBuilder.createScreenCaptureFromPath(screenshot(result.getName(), driver)).build());
			//System.out.println("Test Case Status: " + "PASS" +" Method Name is " +result.getMethod().getMethodName().toString());
		} catch (IOException e) {
			System.out.println("Screenshot not captured");
			e.printStackTrace();
		}
		}
		
	}

	public void onTestFailure(ITestResult result) {
		if(result.getStatus()==result.FAILURE)
		{
		test.log(Status.FAIL, result.getMethod().getMethodName().toString());
		try {
			//test.fail("Test case failed" , MediaEntityBuilder.createScreenCaptureFromPath(screenshot(result.getName(), driver)).build());
			test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot(result.getName(), driver)).build());
			//System.out.println("Test Case Status: " + "FAIL" +" Method Name is " +result.getMethod().getMethodName().toString());
		} catch (IOException e) {
			System.out.println("Screenshot not captured");
			e.printStackTrace();
		}
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test case failed but with success percentage");
		
	}
	public void onStart(ITestContext context) {
		
		//test=report.createTest("onstart");
		System.out.println("Test case started");
		
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/"+"myReport.html");
		
		htmlReport.config().setDocumentTitle("Automation Report");
		htmlReport.config().setReportName("Atomation Testing Report");
		
		report= new ExtentReports();
		report.attachReporter(htmlReport);	
	}

	@AfterTest
	public void onFinish(ITestContext context) {
		System.out.println("Report get's closed");
        report.flush();
      // workbook.close();
 
       // driver.close();
	}
	
	

}
