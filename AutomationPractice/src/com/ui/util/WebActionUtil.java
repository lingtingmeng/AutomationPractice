package com.ui.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class WebActionUtil {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	static WebDriver theDriver = WebDriverUtil.getDriver();
	
	/**
	 * 
	 * @param element
	 * @param driver
	 */
	public static void click(WebElement element, WebDriver driver) {	
		Actions actions = new Actions(driver);
		actions.click(element);
		actions.build().perform();	    
	}
	
	/**
	 * 
	 * @param element
	 * @param driver
	 * @param message
	 */
	public static void clickToSendKeys(WebElement element, WebDriver driver, String inMessage) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(inMessage);
		actions.build().perform();		
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void maxWindow() throws Exception
	{
		if (WebOsUtil.isMac() == true) {		
			theDriver.manage().window().setPosition(new Point(0,0));
           	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
           	Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
           	theDriver.manage().window().setSize(dim);
		} else if (WebOsUtil.isWindows() == true) {
			theDriver.manage().window().maximize();
		}
	}
	
}


	

