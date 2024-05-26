package com.learnAutomation.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static String getpropety(String key) 
	{
		Properties pro=new Properties();
		
		String value=null;
		
		try {
			FileInputStream fis= new FileInputStream(new File(System.getProperty("user.dir")+"./Configuration/config.properties"));
			
			pro.load(fis);
			
			value=pro.getProperty(key);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found"+e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not read file"+e.getMessage());
		}
		
		return value;
		
	}

}




































