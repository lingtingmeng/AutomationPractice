package com.cs.ui.test.nb.webpage.tmp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cs.ks.util.KSLoad;
import com.cs.ks.util.KSLoadInfo;
import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;

public class C8890_AccountRegistration_Wealth extends WebDriverBase{
	
	private static String homeUrlStr,
	regUrlStr, 
	pageHtmlBody, 
	baseFileLocParentStr, 
	testFileLocParentStr, 
	baseFileLocStr, 
	testFileLocStr = null;
	
	static KSLoadInfo ksLoadInfo = KSLoad.getLoadInfo();
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void accountRegistrationWealthHtml() throws Exception
	{		
		homeUrlStr = ksLoadInfo.getHomeURL();
		regUrlStr = homeUrlStr.concat("wealth/register");
		baseFileLocParentStr = ksLoadInfo.getWebOfferingBaseFilesLocation();
		testFileLocParentStr = ksLoadInfo.getWebOfferingTestFilesLocation();
		baseFileLocStr = baseFileLocParentStr.concat("AccountRegistrationWealthHTML.txt");
		testFileLocStr = testFileLocParentStr.concat("AccountRegistrationWealthHTML_Test.txt");
		
		KSU.testMsgOut("Test Case C8890: Account Registration Wealth - " + regUrlStr);
		
		theDriver.get(regUrlStr);
		WebActionUtil.maxWindow();
		Thread.sleep(3000);
		
		if(theDriver.getCurrentUrl().equals(regUrlStr)) {
			WebElement element = WebDriverUtil.isElementPresent(theDriver, By.id("app"), 30);
			pageHtmlBody = (String)((JavascriptExecutor)theDriver).executeScript("return arguments[0].innerHTML;", element);
		} else {
			KSU.error("Not navigated to the correct page !!!");
		}
		
		KSU.writeHtmlToFile(pageHtmlBody, testFileLocStr);
		
		KSU.compareFilesShowDiff(baseFileLocStr, testFileLocStr);
		
		KSU.testMsgOut("Test Case C8890 (Done): Account Registration Wealth");
		
	}
	
}



