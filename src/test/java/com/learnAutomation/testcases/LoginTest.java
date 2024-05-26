package com.learnAutomation.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.learnAutomation.Pages.Loginpage;
import com.learnAutomation.dataProvider.CutomDataProvider;
import com.learnAutomation.fixValues.FixValues;
import com.learnAutomation.helper.BaseClass;

public class LoginTest extends BaseClass
{

	@Test(dataProvider="users", dataProviderClass = CutomDataProvider.class)
	public void logintoApp(String uname, String password)
	{
		Loginpage page=new Loginpage(driver);
		
		page.loginToApplication(uname, password);
		
		 boolean status=page.captureerrormessage().contains(FixValues.userDoesnotExist);
		
		 Assert.assertTrue(status,"Error msg is not displayed as expeccted");


	}

}
