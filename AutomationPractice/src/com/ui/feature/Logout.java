package com.ui.feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ks.util.KSU;
import com.ui.util.WebActionUtil;
import com.ui.util.WebDriverBase;
import com.ui.util.WebDriverUtil;


public class Logout extends WebDriverBase {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	

	/**
	 * 
	 * @throws Exception
	 */
	public static void logout() throws Exception {
		KSU.msgOut("Sign out. ");
		WebElement logoutBtn = WebDriverUtil.isElementPresent(theDriver, By.className("logout"), 30);
		WebActionUtil.click(logoutBtn, theDriver);
		KSU.msgOut("Sign out - Done. ");
	}
	

	
}


	

