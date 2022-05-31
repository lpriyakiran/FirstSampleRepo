package com.salesforce.test.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utilities.CommonUtilities;
import com.salesforce.utilities.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static GenerateReports report;
	
	@BeforeTest
	 public void initialTestSetup() {
		 System.out.println("inside @BeforeTest initialTestSetup method");
		report = GenerateReports.getInstance();
	 }
	
	@Parameters("browser")
	@BeforeMethod
	public static void setUp(String browser) {
		System.out.println("BeforeMethod - testing on "+browser);
		String url = CommonUtilities.getApplicationProperty("url");
		driverSetUp(browser);
		getUrl(url);
	}
	
	@AfterMethod
	 public static void tearDown() {
		System.out.println("AfterMethod");
		closeDriver();
		 //closeAllDrivers();
	 }
	
	@AfterTest
	 public void finalTestTearDown() {
		 System.out.println("@afterTest started");
	 }
	
	public static void driverSetUp(String browser) {
		System.out.println("Driver setup");
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
	}
	
	public static WebDriver getDriverInstance(){
		return driver;
	}
	
	public static void getUrl(String url) {
		driver.get(url);
	}
	
	public static void enterText(WebElement element, String text) {
		element.sendKeys(text);
	}
	
	public static void clearElement(WebElement element) {
		element.clear();
	}
	
	public static void waitUntilVisible(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilTitle(String expectedTitle) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.titleIs(expectedTitle));
	}
	
	public static void waitUntillocatedById(String id) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	public static void waitUntilClickable(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitUntilLocatedBy(By locator) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void closeDriver() {
		driver.close();
	}
	
	public static void closeAllDrivers() {
		driver.quit();
	}

}
