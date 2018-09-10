package com.cs.ui.test.nb.webpage;

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

public class C8898_PrivacyPolicy extends WebDriverBase{
	
	private static String homeUrlStr,
	privacyUrlStr, 
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
	public void privacyPolicyHtml() throws Exception
	{		
		homeUrlStr = ksLoadInfo.getHomeURL();
		privacyUrlStr = homeUrlStr.concat("privacy_policy");
		baseFileLocParentStr = ksLoadInfo.getWebOfferingBaseFilesLocation();
		testFileLocParentStr = ksLoadInfo.getWebOfferingTestFilesLocation();
		baseFileLocStr = baseFileLocParentStr.concat("PrivacyPolicyHTML.txt");
		testFileLocStr = testFileLocParentStr.concat("PrivacyPolicyHTML_Test.txt");
		
		KSU.testMsgOut("Test Case C8898: Privacy Policy Page - " + privacyUrlStr);
		
		theDriver.get(privacyUrlStr);
		WebActionUtil.maxWindow();
		Thread.sleep(3000);
		
		if(theDriver.getCurrentUrl().equals(privacyUrlStr)) {
			WebElement element = WebDriverUtil.isElementPresent(theDriver, By.id("app"), 30);
			pageHtmlBody = (String)((JavascriptExecutor)theDriver).executeScript("return arguments[0].innerHTML;", element);
		} else {
			KSU.error("Not navigated to the correct page !!!");
		}
		
		KSU.writeHtmlToFile(pageHtmlBody, testFileLocStr);
		
		KSU.compareFilesShowDiff(baseFileLocStr, testFileLocStr);
		
		KSU.testMsgOut("Test Case C8898 (Done): Privacy Policy Page");
		
	}
	
}



