package com.ui.feature;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ks.util.KSLoad;
import com.ks.util.KSLoadInfo;
import com.ks.util.KSU;
import com.ui.util.WebActionUtil;
import com.ui.util.WebDriverBase;
import com.ui.util.WebDriverUtil;
import com.ui.util.WebInfoUtil;


public class Login extends WebDriverBase {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	private static String homeUrlStr,loginUrlStr,usernameStr,passwordStr,deUsernameStr,dePasswordStr = null;
	static KSLoadInfo ksLoadInfo = KSLoad.getLoadInfo();
	private static String mpCryptoPassword = "BornToFight";
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void login() throws Exception {
		
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(mpCryptoPassword);
        
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(mpCryptoPassword);
        
		homeUrlStr = ksLoadInfo.getHomeURL();
		loginUrlStr = ksLoadInfo.getLoginURL();
		usernameStr = ksLoadInfo.getUsername();
		passwordStr = ksLoadInfo.getPassword();
		
		deUsernameStr = decryptor.decrypt(usernameStr);
		dePasswordStr = decryptor.decrypt(passwordStr);

		KSU.msgOut("Login with user: " +deUsernameStr);
		KSU.msgOut("Login with password: " +dePasswordStr);
		theDriver.get(homeUrlStr);
		WebActionUtil.maxWindow();
		
		String signInBtnStr = WebInfoUtil.BUTTON_SIGN_IN;
		WebElement signInBtn = WebDriverUtil.isElementPresent(theDriver, By.linkText(signInBtnStr), 30);
		WebActionUtil.click(signInBtn, theDriver);
	
		String URL = theDriver.getCurrentUrl();
		Assert.assertEquals(URL, loginUrlStr);
		
		WebElement username = WebDriverUtil.isElementPresent(theDriver, By.name("email"), 30);
		WebActionUtil.clickToSendKeys(username, theDriver, deUsernameStr);
		
		WebElement password = WebDriverUtil.isElementPresent(theDriver, By.name("passwd"), 30);
		WebActionUtil.clickToSendKeys(password, theDriver, dePasswordStr);
		

		WebElement submitBtn = WebDriverUtil.isElementPresent(theDriver, By.name("SubmitLogin"), 30);
		WebActionUtil.click(submitBtn, theDriver);
	}
	
	/**
	 * 
	 * @param usernameStr
	 * @param passwordStr
	 * @throws Exception
	 */
	public static void loginWithParam(String usernameStr, String passwordStr) throws Exception {
		
		KSU.msgOut("Login with user: " +usernameStr);
		theDriver.get(homeUrlStr);
		WebActionUtil.maxWindow();
		
		String signInBtnStr = WebInfoUtil.BUTTON_SIGN_IN;
		WebElement signInBtn = WebDriverUtil.isElementPresent(theDriver, By.linkText(signInBtnStr), 30);
		WebActionUtil.click(signInBtn, theDriver);
	
		String URL = theDriver.getCurrentUrl();
		Assert.assertEquals(URL, loginUrlStr);
		
		WebElement username = WebDriverUtil.isElementPresent(theDriver, By.name("email"), 30);
		WebActionUtil.clickToSendKeys(username, theDriver, usernameStr);
		
		WebElement password = WebDriverUtil.isElementPresent(theDriver, By.name("passwd"), 30);
		WebActionUtil.clickToSendKeys(password, theDriver, passwordStr);
		
		WebElement submitBtn = WebDriverUtil.isElementPresent(theDriver, By.name("SubmitLogin"), 30);
		WebActionUtil.click(submitBtn, theDriver);
	}

	
}


	

