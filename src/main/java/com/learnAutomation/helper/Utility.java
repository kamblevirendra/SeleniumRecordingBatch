package com.learnAutomation.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.learnAutomation.dataProvider.ConfigReader;

public class Utility {
	
	
	/*
	 * Find the element until its clickable and It will highlight as well
	 */
	
	public static WebElement waitforWebElement(WebDriver driver,By locator)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		
		if(ConfigReader.getpropety("highlighter").contains("true"))
		{
		return highlightWebElement(driver, element);
		}
		else 
		{
		return element;
		}
	}
	
	/*
	 * Find the element until its clickable (custom method)and It will highlight as well
	 */
	public static WebElement waitforElement(WebDriver driver, By locator) 
	{
		WebElement element=null;
		for (int i=0;i<30;i++)
		{
			try {
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					 highlightWebElement(driver, locator);
					 break;
				}
			} catch (Exception e) {
				System.out.println("Waiting for Element condition to be checked");
				wait(1);
			}
		}
		
		return element;	
	}
	
	public static WebElement waitforWebElement(WebDriver driver, By locator, int time) 
	{
		WebElement element=null;
		for (int i=0;i<time;i++)
		{
			try {
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					 highlightWebElement(driver, locator);
					 break;
				}
			} catch (Exception e) {
				System.out.println("Waiting for Element condition to be checked");
				wait(1);
			}
		}
		
		return element;	
	}
	
	public static void wait(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			
		}
	}
	
	
	
	public static WebElement highlightWebElement (WebDriver driver, WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].setAttribute('style','background: yellow;border: solid 2px red')", ele);
		
		wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);
		
		return ele;
	}
	
	
	public static WebElement highlightWebElement (WebDriver driver, By locator)
	{
		WebElement ele=driver.findElement(locator);
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].setAttribute('style','background: yellow;border: solid 2px red')", ele);
		
		wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);
		
		
		
		return ele;
	}
	
	
	
	
	
	
	public static String getCurrentDate()
	{
			
		SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		String newFormat=myformat.format(new Date());
		
		return newFormat;
	}
	
	public static String getCurrentDateNew()
	{
		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}
	
	
	
	
	public  static void captureScreenShot(WebDriver driver)
	{
		try {
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File(".\\ScreenShots\\scrennshot_"+getCurrentDateNew()+".png"));
		} catch (IOException e) {
			System.out.println("Exception is :"+e.getMessage());
		}
	}
	
	public  static String captureScreenShotAsBase64(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		return screenshot;
	}
	
	
	/*
	 * This method will be capture the WebElement Screenshot
	 * @param - driver reference
	 * @param - WebElement reference which we want to take screenshot 
	 */
	public static void captureScreenShotOfWebElement(WebElement ele)
	{
		File scr=ele.getScreenshotAs(OutputType.FILE);
		
		try {
			FileHandler.copy(scr, new File(".\\ScreenShots\\WebElement_"+Utility.getCurrentDateNew() +".png"));
		} catch (IOException e) {
			System.out.println("can't take screenshot of webeleemnt");
		}
	}
	
	
	
	
	/*
	 * This method will wait 15 sec max for alert to appear
	 * @param - WenDriver instance
	 * @return- Alert interface reference   
	 */
	
	public static Alert waitforAlert(WebDriver driver)
	{
		Alert alt=null;
		for(int i=0;i<15;i++)
		{
			
			try
			{
			 alt=driver.switchTo().alert();
			
			break;
			}
			catch(NoAlertPresentException e)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) 
				{
					
				}
				System.out.println("Alert not found....Retrying");
			}
				
		}	
		
		
		return alt;
	}
	
	/*
	 * This method will wait specified sec max for alert to appear
	 * @param - WenDriver instance
	 * @return- Alert interface reference   
	 */

	public static Alert waitforAlert(WebDriver driver, int second)
	{
		Alert alt=null;
		for(int i=0;i<second;i++)
		{
			
			try
			{
			 alt=driver.switchTo().alert();
			
			break;
			}
			catch(NoAlertPresentException e)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) 
				{
					
				}
				System.out.println("Alert not found....Retrying");
			}
				
		}	
		
		
		return alt;
	}
	
	
	
	
	
	public static void selectvaluefromList(WebDriver driver,String xpathvalue, String elementToSearch)
	{
		
		List<WebElement> allvalues=driver.findElements(By.xpath(xpathvalue));
		
		
		for (WebElement ele:allvalues)
		{
			if(ele.getText().contains(elementToSearch))
			{
			ele.click();
			break;
			}
		}
	}
		
	
		public static void selectvaluefromList(WebDriver driver,By locator, String elementToSearch)
		{
			
			List<WebElement> allvalues=driver.findElements(locator);
			
			for (WebElement ele:allvalues)
			{
				if(ele.getText().contains(elementToSearch))
				{
				ele.click();
				break;
				}
			}
		
		
		
	}
	
}
