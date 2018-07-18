package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ks.util.KSU;
import com.ui.feature.Login;
import com.ui.feature.Logout;
import com.ui.util.WebActionUtil;
import com.ui.util.WebDriverBase;
import com.ui.util.WebDriverUtil;
import com.ui.util.WebInfoUtil;


public class TestLogin extends WebDriverBase{

	@Test
	public void login() throws Exception
	{		

		KSU.testMsgOut("Test Case 1: Sign in successfully");

		//login
		Login.login();
		String xpathMyAccount = "//span[contains(@class,'navigation_page') and text()='"+WebInfoUtil.TEXT_MY_ACCOUNT+"']";
		WebElement myAccount = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathMyAccount), 30);
		String myAccountStr = myAccount.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_MY_ACCOUNT, myAccountStr);

		KSU.testMsgOut("Test Case 1 (Done): Sign in successfully");
		Logout.logout();
	}

	@Test
	public void loginWithEmptyEmail() throws Exception
	{		

		KSU.testMsgOut("Test Case 2: Sign in with an empty email address (Negative)");

		//login with parameters
		String usernameStr = "";
		String passwordStr = "12345";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_EMAIL_REQUIRED, errorMsgStr);

		KSU.testMsgOut("Test Case 2 (Done): Sign in with an empty email address (Negative)");
	}

	@Test
	public void loginWithEmptyPwd() throws Exception
	{		

		KSU.testMsgOut("Test Case 3: Sign in with an empty password (Negative)");

		//login with parameters
		String usernameStr = "meltingkevin@hotmail.com";
		String passwordStr = "";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol/li";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_PWD_REQUIRED, errorMsgStr);

		KSU.testMsgOut("Test Case 3 (Done): Sign in with an empty password (Negative)");
	}

	@Test
	public void loginWithEmptyEmailAndPwd() throws Exception
	{		

		KSU.testMsgOut("Test Case 4: Sign in with an empty email and password (Negative)");

		//login with parameters
		String usernameStr = "";
		String passwordStr = "";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_EMAIL_REQUIRED, errorMsgStr);

		KSU.testMsgOut("Test Case 4 (Done): Sign in with an empty email and password (Negative)");
	}

	@Test
	public void loginWithInvalidEmailNoPwd() throws Exception
	{		

		KSU.testMsgOut("Test Case 5: Sign in with an invalid email and empty password (Negative)");

		//login with parameters
		String usernameStr = "newfew";
		String passwordStr = "";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol/li";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_INVALID_EMAIL, errorMsgStr);

		KSU.testMsgOut("Test Case 5 (Done): Sign in with an invalid email and empty password (Negative)");

	}

	@Test
	public void loginWithInvalidEmail() throws Exception
	{		

		KSU.testMsgOut("Test Case 6: Sign in with an invalid email and any password (Negative)");

		//login with parameters
		String usernameStr = "newfew";
		String passwordStr = "ewqwq";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol/li";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_INVALID_EMAIL, errorMsgStr);

		KSU.testMsgOut("Test Case 6 (Done): Sign in with an invalid email and any password (Negative)");
	}

	@Test
	public void loginWithIncorrectPwd() throws Exception
	{		

		KSU.testMsgOut("Test Case 7: Sign in with a valid email and incorrect password (Negative)");

		//login with parameters
		String usernameStr = "meltingkevin@hotmail.com";
		String passwordStr = "213123";
		Login.loginWithParam(usernameStr, passwordStr);
		
		String xpathError = "//div[contains(@class,'alert alert-danger') ]/ol/li";
		WebElement errorMsg = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathError), 30);
		String errorMsgStr = errorMsg.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_ERROR_AUTH_FAILED, errorMsgStr);

		KSU.testMsgOut("Test Case 7 (Done): Sign in with a valid email and incorrect password (Negative)");
	}

	@Test
	public void loginForgotPwdLink() throws Exception
	{		

		KSU.testMsgOut("Test Case 8: Forgot your password link");

		String loginUrl = "http://automationpractice.com/index.php?controller=authentication";
		theDriver.get(loginUrl);
		WebActionUtil.maxWindow();
		//theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	

		WebElement forgotPwdLink = WebDriverUtil.isElementPresent(theDriver, By.linkText(WebInfoUtil.LINKED_TEXT_FORGOT_PWD), 30);
		WebActionUtil.click(forgotPwdLink, theDriver);

		String URL = theDriver.getCurrentUrl();
		String forgotPwdURL = "http://automationpractice.com/index.php?controller=password";
		Assert.assertEquals(URL, forgotPwdURL);

		String xpathForgotPwd = "//div[contains(@class,'box')]//h1";
		WebElement forgotPwd = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathForgotPwd), 30);
		String forgotPwdStr = forgotPwd.getText();
		Assert.assertEquals(WebInfoUtil.TEXT_FORGOT_PWD, forgotPwdStr);

		KSU.testMsgOut("Test Case 8 (Done): Forgot your password link");
	}

}
