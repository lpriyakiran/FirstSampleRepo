package com.salesforce.pages.base;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.utilities.CommonUtilities;
import com.salesforce.utilities.GenerateReports;

public class BasePage {
	
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	public static GenerateReports report;
	
	public BasePage(WebDriver driver1) {
		driver=driver1;
		PageFactory.initElements(driver, this);
		report=GenerateReports.getInstance();
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
	
	public static void clickButton(WebElement element) {
		element.click();
	}
	
	public static void clickElement(WebElement element) {
		element.click();
	}
	
	public static void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	public static void switchToWindow(String[] arr, int index) {
		driver.switchTo().window(arr[index]);
	}
	
	public static String[] handleWindows() {
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		return getWindow;
	}
	
//	public static void validatePageHeader(String header) {
//		waitUntilLocatedBy(By.xpath("//h1[contains(text(),'Opportunity Report')]"));
//		WebElement pageHeaderName = driver.findElement(By.xpath("//h1[contains(text(),'Opportunity Report')]"));
//		return pageHeaderName.getText();
//	}
}
