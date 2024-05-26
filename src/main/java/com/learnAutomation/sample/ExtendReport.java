package com.learnAutomation.sample;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReport {
	
	@Test
	public void test1()
	{
		/*
		 * ExtentSparkReporter - FileName, Path. config > theme,chart,RepornName, title
		 * ExtentReport- main class to generate report
		 * ExtentTest- Responsible to maintain test with report
		 * 			logs
		 * 				PASS, FAIL, SKIP, FATAL, ERROR, Warning etc
		 * 
		 * flush ()- ExtentReport- Write to report
		 * MediaEntityBuilder- screenshot
		 * 
		 */
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/AutomationReport.html");
		
		reporter.config().setTheme(Theme.STANDARD);
		
		reporter.config().setReportName("Automation report");
		
		reporter.config().setDocumentTitle("Sprint 1 report");
		
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		
		ExtentTest test1=extent.createTest("Login");
		
		test1.log(Status.INFO, "user is able to enter email");
		test1.log(Status.INFO, "user is able to enter password");
		test1.log(Status.INFO, "user is able to click on login button");
		test1.log(Status.PASS, "user is not able see dashboard");
		
		ExtentTest test2=extent.createTest("Payment");
		test2.info("user is able to enter card details");
		test2.info("user is able to enter CVV details");
		test2.fail("OTP not received", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\hp\\OneDrive\\Desktop\\javaimg.jpg", "current status").build());
		
		ExtentTest test3=extent.createTest("Logout");
		test3.info("user clicked on logout");
		test3.skip("user is not able to see login page");
		
		extent.flush();	
	}

}

		/*
		 * test name- we need to take name dynamically- test method name will be our test name
		 * log event-automatically-	based on that we will call the method
		 * report name-it should be with timestamp
		 * screenshot- if get the test status is fail then only call screenshot- screenshot of last event
		 */


