package com.cs.ui.feature;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cs.ks.util.KSLoad;
import com.cs.ks.util.KSLoadInfo;
import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;


public class Login extends WebDriverBase {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	private static String homeUrlStr,loginUrlStr,usernameStr,passwordStr,dropdownUserInfoStr,deUsernameStr,dePasswordStr = null;
	static KSLoadInfo ksLoadInfo = KSLoad.getLoadInfo();
	private static String mpCryptoPassword = "BornToFight";
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void loginFromHome() throws Exception {	
		
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(mpCryptoPassword);
        
		homeUrlStr = ksLoadInfo.getHomeURL();
		usernameStr = ksLoadInfo.getUsername();
		passwordStr = ksLoadInfo.getPassword();
		deUsernameStr = decryptor.decrypt(usernameStr);
		dePasswordStr = decryptor.decrypt(passwordStr);
		
		KSU.msgOut("Login from home page with user: " +deUsernameStr);
		theDriver.get(homeUrlStr);
		WebActionUtil.maxWindow();
		
		String xpathSignInButton = WebInfoUtil.BUTTON_SIGN_IN;
		WebElement signInBtn = WebDriverUtil.isElementPresent(theDriver, By.linkText(xpathSignInButton), 30);
		WebActionUtil.click(signInBtn, theDriver);

		WebElement username = WebDriverUtil.isElementPresent(theDriver, By.name("username"), 30);
		WebActionUtil.clickToSendKeys(username, theDriver, deUsernameStr);
		
		WebElement password = WebDriverUtil.isElementPresent(theDriver, By.name("password"), 30);
		WebActionUtil.clickToSendKeys(password, theDriver, dePasswordStr);
		
		String xpathSubmitButton = "//button[@type='submit']";
		WebElement submitBtn = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathSubmitButton), 30);
		WebActionUtil.click(submitBtn, theDriver);
		
		String xpathUsername = "//p[contains(@class, 'styles__fullName__3abOT') and text()='"+deUsernameStr+"']";
		WebElement dropdownUserInfo = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathUsername), 30);
		dropdownUserInfoStr = dropdownUserInfo.getText();
		
		Assert.assertEquals(dropdownUserInfoStr,deUsernameStr);
		KSU.msgOut("Login from home page - Done.");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void login() throws Exception {
		
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(mpCryptoPassword);
        
		loginUrlStr = ksLoadInfo.getLoginURL();
		usernameStr = ksLoadInfo.getUsername();
		passwordStr = ksLoadInfo.getPassword();
		deUsernameStr = decryptor.decrypt(usernameStr);
		dePasswordStr = decryptor.decrypt(passwordStr);

		KSU.msgOut("Login with user: " +deUsernameStr);
		theDriver.get(loginUrlStr);
		WebActionUtil.maxWindow();
		
		WebElement username = WebDriverUtil.isElementPresent(theDriver, By.name("username"), 30);
		WebActionUtil.clickToSendKeys(username, theDriver, deUsernameStr);
		
		WebElement password = WebDriverUtil.isElementPresent(theDriver, By.name("password"), 30);
		WebActionUtil.clickToSendKeys(password, theDriver, dePasswordStr);
		
		String xpathSubmitButton = "//button[@type='submit']";
		WebElement submitBtn = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathSubmitButton), 30);
		WebActionUtil.click(submitBtn, theDriver);
		
		String xpathUsername = "//p[contains(@class, 'styles__fullName__3abOT') and text()='"+deUsernameStr+"']";
		WebElement dropdownUserInfo = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathUsername), 30);
		dropdownUserInfoStr = dropdownUserInfo.getText();
		
		Assert.assertEquals(dropdownUserInfoStr,deUsernameStr);
		KSU.msgOut("Login - Done.");
	}

	
}


	

