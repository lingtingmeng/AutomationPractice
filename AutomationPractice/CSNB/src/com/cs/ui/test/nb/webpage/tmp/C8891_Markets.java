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

public class C8891_Markets extends WebDriverBase{
	
	private static String homeUrlStr,
	marketsUrlStr, 
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
	public void marketsHtml() throws Exception
	{		
		homeUrlStr = ksLoadInfo.getHomeURL();
		marketsUrlStr = homeUrlStr.concat("markets/bitcoin");
		baseFileLocParentStr = ksLoadInfo.getWebOfferingBaseFilesLocation();
		testFileLocParentStr = ksLoadInfo.getWebOfferingTestFilesLocation();
		baseFileLocStr = baseFileLocParentStr.concat("MarketsHTML.txt");
		testFileLocStr = testFileLocParentStr.concat("MarketsHTML_Test.txt");
		
		KSU.testMsgOut("Test Case C8891: Markets Page - " + marketsUrlStr);
		
		theDriver.get(marketsUrlStr);
		WebActionUtil.maxWindow();
		Thread.sleep(3000);
		
		if(theDriver.getCurrentUrl().equals(marketsUrlStr)) {
			WebElement element = WebDriverUtil.isElementPresent(theDriver, By.id("app"), 30);
			pageHtmlBody = (String)((JavascriptExecutor)theDriver).executeScript("return arguments[0].innerHTML;", element);
		} else {
			KSU.error("Not navigated to the correct page !!!");
		}
		
		KSU.writeHtmlToFile(pageHtmlBody, testFileLocStr);
		
		KSU.compareFilesShowDiff(baseFileLocStr, testFileLocStr);
		
		KSU.testMsgOut("Test Case C8891 (Done): Markets Page");
		
	}
	
}



