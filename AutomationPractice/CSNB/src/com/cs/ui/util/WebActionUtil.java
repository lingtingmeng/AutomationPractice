package com.cs.ui.util;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.cs.ks.util.KSU;


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
	 * @param inPanel
	 * @param inDropdownLst
	 * @param inItem
	 * @throws Exception
	 */
	public static void inputDropdown(String inPanel, String inDropdownLst, String inItem) throws Exception
	{
		if (inPanel == WebInfoUtil.PANEL_TRANSACTIONS ) {
			if (inDropdownLst == WebInfoUtil.DROPDOWN_ALL) {
				String timeDropdownXpath = "//span[contains(@class, 'Select-value-') and text()='all']";
				WebElement timeDropdown = WebDriverUtil.isElementPresent(theDriver, By.xpath(timeDropdownXpath), 30);
				
				WebActionUtil.clickToSendKeys(timeDropdown, theDriver, inItem);
				Actions action= new Actions(theDriver);		
				action.sendKeys(Keys.ENTER).perform();
			}
		} else if (inPanel == WebInfoUtil.PANEL_HOLDINGS) {

			if (inDropdownLst == WebInfoUtil.DROPDOWN_HOLDINGS) {
				String holdingsSortXpath = "//div[contains(@class,'styles__title__')]/b[text()='"+WebInfoUtil.DROPDOWN_HOLDINGS+"']";	
				WebElement holdingsSort = WebDriverUtil.isElementPresent(theDriver, By.xpath(holdingsSortXpath), 30);				
				WebElement parentDiv = holdingsSort.findElement(By.xpath(".."));
				WebElement sortDropdown = parentDiv.findElement(By.xpath("following-sibling::*[1]/div/div/span/div[contains(@class, 'Select-placeholder') and text()='Select...']"));

				WebActionUtil.clickToSendKeys(sortDropdown, theDriver, inItem);
				Actions action= new Actions(theDriver);		
				action.sendKeys(Keys.ENTER).perform();
			}
		}
		
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
	
	/**
	 * 
	 * @param inPanel
	 * @param inDropdownLst
	 * @param inItem
	 * @throws Exception
	 */
	public static void selectDropdown(String inPanel, String inDropdownLst, String inItem) throws Exception
	{
		if (inPanel == WebInfoUtil.PANEL_QUICKTRADE ) {
			
			String tradeXpath = "//span[text()='"+inDropdownLst+"']";
			WebElement trade = WebDriverUtil.isElementPresent(theDriver, By.xpath(tradeXpath), 30);
			WebElement parentDiv = trade.findElement(By.xpath("../../.."));
			WebElement xpathTradeDropdown = parentDiv.findElement(By.xpath("following-sibling::*[1]/div/div/div/span/div[contains(@class,'Select-placeholder') and text()='"+WebInfoUtil.TEXT_SELECT+"']"));
			//WebElement xpathTradeDropdown = parentDiv.findElement(By.xpath("following-sibling::*[1]/div/div/div/div/div[contains(@class,'Select-placeholder') and text()='"+WebInfoUtil.TEXT_SELECT+"']"));
			click(xpathTradeDropdown, theDriver);
			Thread.sleep(5000);
			
			WebElement dropdownElm = WebDriverUtil.isElementPresent(theDriver, By.className("Select-menu-outer"), 30);
			
			String dropdownOptions = dropdownElm.getText();
			String[] dropdownOptionsStr = dropdownOptions.split("\\r?\\n");
			int length = dropdownOptionsStr.length;
			int inItemIdx = 0;
		    for (int i =0; i<length; i++) {
		    		String text = dropdownOptionsStr[i];
		    		if(text.equals(inItem)) {
		    			inItemIdx = i;
		    			break;
		    		}
		    }
		    
			Actions action= new Actions(theDriver);		
			for(int i=0; i<inItemIdx; i++) {
				action.sendKeys(Keys.ARROW_DOWN).perform();
				Thread.sleep(1000);
			}
			
			action.sendKeys(Keys.ENTER).perform();	
			
		}
		
		KSU.out("Selected Currency: "+inItem);
	    Thread.sleep(2000);
	}
	
}


	

