package com.salesforce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.test.base.BaseTest;
import com.salesforce.pages.home.Home;
import com.salesforce.pages.login.Login;
import com.salesforce.utilities.CommonUtilities;

public class SFDCLogin extends BaseTest{
	
	@Test
	public static void emptyPasswordError() {
		report.logTestInfo("Test case - emptyPasswordError");
		Login login = new Login(driver);
		login.emptyPasswordLogin();
		String expectedErrorMsg = CommonUtilities.getApplicationProperty("emptypwderror");
		String errorMsg = login.checkEmptyPasswordErrorMsg();
		Assert.assertEquals(errorMsg, expectedErrorMsg);
	}
	
	@Test
	public static void loginToSFDC() {
		report.logTestInfo("Test case - Successful login");
		Login login = new Login(driver);
		login.login();
		String homeTitle = CommonUtilities.getApplicationProperty("homepagetitle");
		waitUntilTitle(homeTitle);
	}
	
	@Test
	public static void checkRememberMe() throws InterruptedException {
		report.logTestInfo("Test case - Remember me");
		Login login = new Login(driver);
		login.loginWithRememberMe();
		waitUntillocatedById("userNavButton");
		Home.userNavigation();
		Home.logout();
		Thread.sleep(3000);
		String expected = CommonUtilities.getApplicationProperty("username");
		String actual = login.checkUsernameRemember();
		Assert.assertEquals(actual, expected);		
	}
	
	@Test
	public static void forgotPassword() {
		report.logTestInfo("Test case - Forgot pwd");
		Login login = new Login(driver);
		login.clickForgotPassword();
		login.forgotPasswordProcess();
		boolean actual = login.checkForgotPwdEmailMsg();
		Assert.assertEquals(actual, true);
	}
	
	@Test
	public static void loginErrorMessage() {
		report.logTestInfo("Test case - Login error");
		Login login = new Login(driver);
		login.login(CommonUtilities.getApplicationProperty("wrongusername"), CommonUtilities.getApplicationProperty("wrongpwd"));
		boolean actual = login.checkLoginErrorMsg();
		Assert.assertEquals(actual, true);
	}
}
