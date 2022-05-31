package com.salesforce.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.test.base.BaseTest;

public class GenerateReportsListener implements ITestListener{
	
	ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public GenerateReports report = GenerateReports.getInstance();

	public void onTestStart(ITestResult result) {
		System.out.println("Inside GenerateReportsListener onTestStart() method creating test report");
		report.startTestReport(result.getMethod().getMethodName());
		System.out.println("onTestStart completed");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(" Inside onTestSuccess Listener method");
		report.logTestpassed(result.getMethod().getMethodName() );
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = BaseTest.getDriverInstance();
		String screenshotPath = CommonUtilities.takescreenshot(driver);
		System.out.println(screenshotPath);
		report.logTestFailed(result.getMethod().getMethodName());
		try {
			report.attachScreeshot(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		report.logTestSkipped(result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		System.out.println("Inside GenerateReportsListener onStart() method creating report");
		report.startExtentReport();
	}

	public void onFinish(ITestContext context) {
		report.endReport();
	}

}
