package com.learnAutomation.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.learnAutomation.Factory.BrowserFactory;
import com.learnAutomation.helper.Utility;

public class ExtentTestNGITestListener implements ITestListener {

	ExtentReports extent = ExtentManager.getInsatance();

	// ExtentTest extentTest;

	ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();

	public synchronized void onTestStart(ITestResult result) {

		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());

		parentTest.set(extentTest);

		System.out.println("LOG:INFO-Test Started !!!" + result.getMethod().getMethodName());
	}

	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("LOG:END-Test Ended !!!" + context.getName());
	}

	public synchronized void onTestSuccess(ITestResult result) {
		parentTest.get().pass("Test passed");
		System.out.println("LOG:PASS-Test Passed !!!" + result.getMethod().getMethodName());
	}

	public synchronized void onTestSkipped(ITestResult result) {
		parentTest.get().skip("Test skipped");
		System.out.println("LOG:SKIP-Test Skipped !!!" + result.getMethod().getMethodName());
		System.out.println("Exception Trace :" + result.getThrowable().getMessage());
	}

	public synchronized void onTestFailure(ITestResult result) {

		String screenshots = Utility.captureScreenShotAsBase64(BrowserFactory.getDriver());
		parentTest.get().fail("Test Failed :" + result.getThrowable().getMessage(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(screenshots, "screenshot in failed").build());
		System.out.println("LOG:FAIL-Test Failed !!!" + result.getMethod().getMethodName());
		System.out.println("Exception Trace :" + result.getThrowable().getMessage());
	}

}