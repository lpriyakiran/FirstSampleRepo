package com.salesforce.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;

import static com.salesforce.utilities.Constants.APPLICATION_PROPERTIES_PATH;

public class CommonUtilities {
	
	public static String getApplicationProperty(String key) {

		Properties properties = new Properties();
		String filePath = APPLICATION_PROPERTIES_PATH;

		FileInputStream inputfile = null;
		try {
			inputfile = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String value = null;

		try {
			try {
				properties.load(inputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			value = properties.getProperty(key);
			System.out.println("Property value for key "+key+" is " +value);
		} finally {
			try {
				inputfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public static String takescreenshot(WebDriver driver) {
		GenerateReports report = GenerateReports.getInstance();
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = (TakesScreenshot)driver;
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		report.logTestInfo("Screenshot captured");
		String filePath= Constants.SCREENSHOT_PATH+"salesforce.jpg";
		File DestFile =  new File(filePath);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
