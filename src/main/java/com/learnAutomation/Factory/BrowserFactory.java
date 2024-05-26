package com.learnAutomation.Factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.learnAutomation.dataProvider.ConfigReader;

public class BrowserFactory {
	
	static WebDriver driver=null;
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	
	
	public static WebDriver startBrowser(String BrowserName, String applicationURL)
	{
		
		
		if(BrowserName.equalsIgnoreCase("chrome") || BrowserName.equalsIgnoreCase("Google chrome") || BrowserName.equalsIgnoreCase("GC"))
		{
			ChromeOptions opt= new ChromeOptions();
			
			if(ConfigReader.getpropety("headless").contains("true"))
			{
			opt.addArguments("--headless=new");
			}
			driver=new ChromeDriver(opt);
		}
		
		else if(BrowserName.equalsIgnoreCase("Edge") || BrowserName.equalsIgnoreCase("Microsoft Edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("FireFox") || BrowserName.equalsIgnoreCase("FF"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Sorry we do not support "+BrowserName+"browser");
		}
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getpropety("pageload"))));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getpropety("scripttimeout"))));
		
		driver.get(applicationURL);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getpropety("implicitwait"))));
		
		return driver;
	}
	
	
	

}
