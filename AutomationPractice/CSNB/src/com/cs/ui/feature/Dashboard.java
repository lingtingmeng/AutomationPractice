package com.cs.ui.feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;


public class Dashboard extends WebDriverBase{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void dashboardTab() throws Exception
	{
		KSU.msgOut("Click Dashboard tab");
		String xpathDashboard = WebInfoUtil.TAB_DASHBOARD;
		WebElement dashboard = WebDriverUtil.isElementPresent(theDriver, By.linkText(xpathDashboard), 30);
		WebActionUtil.click(dashboard, theDriver);
	}
	

	
}
