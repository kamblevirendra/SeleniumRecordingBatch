package com.learnAutomation.helper;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.learnAutomation.Factory.BrowserFactory;
import com.learnAutomation.dataProvider.ConfigReader;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeClass
	public void setupApplication() {
		
		System.out.println("Running before class");
		
		driver=BrowserFactory.startBrowser(ConfigReader.getpropety("browser"), ConfigReader.getpropety("URL"));
	}
	@AfterClass
	public void tearDown()
	{
		System.out.println("Running after class");
		driver.quit();
	}

}
