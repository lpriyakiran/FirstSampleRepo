package com.salesforce.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.base.BasePage;
import com.salesforce.utilities.CommonUtilities;

public class Login extends BasePage{
	WebDriver driver;
	
	@FindBy(id = "username") WebElement username;
	@FindBy(id = "password") WebElement passwrd;
	@FindBy(id = "Login") WebElement loginButton;
	@FindBy(id = "rememberUn") WebElement rememberCheckBox;
	@FindBy(id = "error") WebElement errorMsg;
	@FindBy(id = "forgot_password_link") WebElement forgotPassword;
	@FindBy(xpath = "//div[@id='error']") WebElement message;
	@FindBy(id = "idcard-identity") WebElement rememberName;
	@FindBy(xpath = "//input[@id='continue']") WebElement continueButton;
	@FindBy(xpath = "//input[@type='email']") WebElement enterUsername;
	@FindBy(xpath = "//a[text()='Return to Login']") WebElement returnToLoginBtn;
	@FindBy(xpath = "//h1[@id='header'][text()='Check Your Email']") WebElement checkEmailmsg;

	protected static String userName= CommonUtilities.getApplicationProperty("username");
	protected static String password= CommonUtilities.getApplicationProperty("password");
	
	public Login(WebDriver driver) {
		super(driver);
	}
	
	public void login() {
		waitUntillocatedById("username");
		enterText(username, userName);
		enterText(passwrd, password);
		loginClick();
	}
	
	public void login(String user, String pass) {
		waitUntilVisible(username);
		enterText(username, user);
		enterText(passwrd, pass);
		loginClick();
	}
	
	public void loginClick() {
		clickButton(loginButton);
	}
	
	public void emptyPasswordLogin() {
		waitUntilVisible(username);
		enterText(username, userName);
		clearElement(passwrd);
		SoftAssert assert1 = new SoftAssert();
		assert1.assertTrue(passwrd.getText().isBlank(), "Password field has been cleared.");
		loginClick();
	}
	
	public void loginWithRememberMe() {
		waitUntilVisible(username);
		enterText(username, userName);
		enterText(passwrd, password);
		rememberCheckBox.click();
		loginClick();
	}
	
	public boolean checkLoginErrorMsg() {
		return errorMsg.isDisplayed();
	}
	
	public void clickForgotPassword() {
		waitUntilVisible(username);
		enterText(username, userName);
		clickElement(forgotPassword);
	}
	
	public String checkEmptyPasswordErrorMsg() {
		waitUntilLocatedBy(By.xpath("//div[@id='error']"));
		return message.getText();
	}
	
	public String checkUsernameRemember() {
		waitUntilVisible(rememberName);
		return rememberName.getText();
	}
	
	public void forgotPasswordProcess() {
		By locator = By.xpath("//h1[@id='header'][text()='Forgot Your Password']");
		waitUntilLocatedBy(locator);
		enterText(enterUsername, userName);	
		clickButton(continueButton);
		waitUntilVisible(returnToLoginBtn);
	}
	
	public boolean checkForgotPwdEmailMsg() {
		return checkEmailmsg.isDisplayed();
	}
}
