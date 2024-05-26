package com.learnAutomation.dataProvider;

import org.testng.annotations.DataProvider;

public class CutomDataProvider {
	
	@DataProvider(name="users")
	public static Object[][] getLoginData()
	{
		Object[][] arr=ExcelReader.getDataFromSheet("Users");
		return arr;	
	}

}
