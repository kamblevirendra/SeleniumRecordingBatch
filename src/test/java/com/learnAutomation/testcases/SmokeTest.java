package com.learnAutomation.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.learnAutomation.Pages.Loginpage;
import com.learnAutomation.dataProvider.CutomDataProvider;
import com.learnAutomation.fixValues.FixValues;
import com.learnAutomation.helper.BaseClass;

public class SmokeTest extends BaseClass
{

	@Test
	public void verifyURL()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("login"),"URL does not contain login");

	}

}
