package com.learnAutomation.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learnAutomation.helper.Utility;

public class ExtentManager {
	

	public static ExtentReports extent;
	
	public static ExtentReports getInsatance()
	{
		
		if(extent==null)
		{
			extent=createInstance();
			return extent;
		}
		else
		{
			return extent;
		}
	}
	
	
	
	
	
	public static ExtentReports createInstance()
	{
		ExtentSparkReporter sparkReport=new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/Automation_"+Utility.getCurrentDateNew()+".html");
		
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setDocumentTitle("Automation Report");
		sparkReport.config().setReportName("Sprint 1 Report");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(sparkReport);
		
		return extent;
	}

}
