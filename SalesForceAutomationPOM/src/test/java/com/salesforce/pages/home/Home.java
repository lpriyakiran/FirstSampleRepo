package com.salesforce.pages.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salesforce.pages.base.BasePage;

public class Home extends BasePage{
	
	public Home(WebDriver driver1) {
		super(driver1);
	}

	protected static WebElement myProfile = driver.findElement(By.xpath("//a[@title='My Profile']"));
	protected static WebElement contactsTab = driver.findElement(By.xpath("//a[@title = 'Contacts Tab'][text()='Contacts']"));
	protected static WebElement accountsTab = driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
	
	public static void userNavigation() {
		WebElement navButton = driver.findElement(By.id("userNavButton"));
		waitUntilClickable(navButton);
		clickButton(navButton);
	}
	
	public static void logout() {
		WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		waitUntilClickable(logout);
		clickButton(logout);
	}
	
	public static void myProfile() {
		waitUntilClickable(myProfile);
		clickElement(myProfile);
	}
	
	public static void goToUserMenu() {
		System.out.println("goToUserMenu");
		waitUntillocatedById("userNavButton");
		Home.userNavigation();
	}
	
	public static void goToAccountsTab() {
		waitUntilClickable(accountsTab);
		clickElement(accountsTab);
	}
	
	public static void goToOpportunitiesTab() {
		WebElement opportunitiesTab = driver.findElement(By.xpath("//a[@title='Opportunities Tab']"));
		waitUntilClickable(opportunitiesTab);
		clickElement(opportunitiesTab);
	}
	
	public static void goToLeadsTab() {
		WebElement leadsTab = driver.findElement(By.xpath("//a[@title='Leads Tab']"));
		waitUntilClickable(leadsTab);
		clickElement(leadsTab);
	}
	
	public static void goToContactsTab() {
		waitUntilClickable(contactsTab);
		clickElement(contactsTab);
	}
	
	public static void goToHomeTab() {
		WebElement homeTab = driver.findElement(By.xpath("//a[@title='Home Tab']"));
		waitUntilClickable(homeTab);
		clickElement(homeTab);
	}
	
	public static void closeDialog() {
		By locator = By.xpath("//a[@title='Close'][text()='Close']");
		waitUntilLocatedBy(locator);
		//TODO- Add a check if the alert is present or not
		driver.findElement(locator).click();
	}
	
	public static void checkUserMenuItems() {
		List<WebElement> userMenuItems = driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));
		int items = userMenuItems.size();
		System.out.println("Number of menu items: "+items);
		for(WebElement menu:userMenuItems) {
			String menuItem = menu.getText();
			menu.isDisplayed();
			System.out.println(menuItem);
		}
	}
	
	public static void myProfileEdit() throws Exception {
		
		Thread.sleep(3000);
		WebElement moderatorBtn = driver.findElement(By.xpath("//a[@id='moderatorMutton'][@title='User Action Menu']"));
		waitUntilClickable(moderatorBtn);
		moderatorBtn.click();
		WebElement editProfile = driver.findElement(By.xpath("//a[@title='Edit Profile']"));
		editProfile.click();
	}
	
	public static void devConsole() {
		By locator = By.xpath("//a[@title='Developer Console (New Window)']");
		waitUntilLocatedBy(locator);
		WebElement devConsole = driver.findElement(locator);
		clickElement(devConsole);
	}
	
	public static void editProfile() {
		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		lastName.sendKeys("Last");
		clickButton(driver.findElement(By.xpath("//input[@type='button'][@value='Save All']")));
		WebElement navBtn = driver.findElement(By.id("userNavButton"));
		waitUntilClickable(navBtn);
	}
	
	public static void sharePost() {
		//post text
		WebElement postLink = driver.findElement(By.xpath("//textarea[@id='publishereditablearea']"));
		clickElement(postLink);
		WebElement shareBtn = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickButton(shareBtn);
	}
	
	public static void fileLink() {
		waitUntilVisible(driver.findElement(By.xpath("//p[contains(text(),'hello')]")));
		WebElement fileLink = driver.findElement(By.xpath("//a[@id='publisherAttachContentPost']"));
		fileLink.click();
		waitUntilVisible(driver.findElement(By.xpath("//p[contains(text(),'Upload a file')]")));
	}
	
	public static void addPhoto() {
		WebElement photo = driver.findElement(By.xpath("//img[@class='chatter-photo']"));
		Actions action = new Actions(driver);
		action.moveToElement(photo).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'Add Photo')]")).click();
		driver.switchTo().frame("uploadphotoContentId");
		driver.findElement(By.xpath("//input[@type='button'][@value='Cancel']"));
	}
	
	public static void devConsoleWindow() throws Exception {
		String[] windows = handleWindows();
		switchToWindow(windows, 1);
		Thread.sleep(2000);
		driver.close();
		switchToWindow(windows, 0);
		waitUntillocatedById("userNavButton");
	}
}
