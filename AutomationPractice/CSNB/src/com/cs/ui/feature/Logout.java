package com.cs.ui.feature;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cs.ks.util.KSLoad;
import com.cs.ks.util.KSLoadInfo;
import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;


public class Logout extends WebDriverBase{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	private static String usernameStr, deUsernameStr = null;
	static KSLoadInfo ksLoadInfo = KSLoad.getLoadInfo();
	private static String mpCryptoPassword = "BornToFight";
    
	/**
	 * 
	 * @param inUsername
	 * @throws Exception
	 */
	public static void logout() throws Exception {
		
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
	    decryptor.setPassword(mpCryptoPassword);

		usernameStr = ksLoadInfo.getUsername();
		deUsernameStr = decryptor.decrypt(usernameStr);
		
		KSU.msgOut("Log Out with user: " + deUsernameStr);
		String xpathUsername = "//p[contains(@class, 'styles__fullName__3abOT') and text()='"+deUsernameStr+"']";

		WebElement dropdownUserInfo = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathUsername), 30);
		WebActionUtil.click(dropdownUserInfo, theDriver);
		Thread.sleep(3000);
		
		String xpathLogOut = "//div[contains(@class, 'styles__text') and text()='"+WebInfoUtil.DROPDOWN_LOG_OUT+"']";
		WebElement dropdownLogOut = WebDriverUtil.isElementPresent(theDriver, By.xpath(xpathLogOut), 30);
		WebActionUtil.click(dropdownLogOut, theDriver);
		KSU.msgOut("Log Out - Done.");
	}
	
}


	

