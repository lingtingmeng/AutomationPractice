package com.ui.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ks.util.KSU;


public class WebDriverUtil {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
    private static WebDriver sDriver = null;
    
    /**
     * 
     */
    private static void _init_driver_()
    {
         WebDriverFactory.BrowserType type = null;
         if (WebOsUtil.isMac() == true) {     
        	 type = WebDriverFactory.BrowserType.CHROME;
         } else if (WebOsUtil.isWindows() == true) {
         } else if (WebOsUtil.isLinux() == true) {
         } 
         sDriver = WebDriverFactory.getDriver(type);            
    }
    
    /**
     * 
     * @return
     */
    public static WebDriver getDriver()
    {
         WebDriver rval = sDriver;
         if(rval == null) {
              _init_driver_();
              rval = sDriver;
         }
         return rval;
    }
	
	
	/**
	 * 
	 * @param driver
	 * @param by
	 * @param time
	 * @return
	 */
	public static WebElement isElementPresent(WebDriver driver,By by,int time) { 
		WebElement ele = null;
		for(int i=0;i<time;i++) {
			try{
				ele=driver.findElement(by);
				break;
			}
			catch(Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					KSU.error("Element not appear on DOM!");
				}
			} 
		}
		return ele;	 
	}
	
	/**
	 * 
	 * @param driver
	 * @param by
	 * @param time
	 * @return
	 */
	public static List<WebElement> areElementsPresent(WebDriver driver,By by,int time) { 
		List<WebElement> ele = null;
		for(int i=0;i<time;i++) {
			try{
				ele=driver.findElements(by);;
				break;
			}
			catch(Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					KSU.error("Element not appear on DOM!");
				}
			} 
		}
		return ele;	 
	}
	
	/**
	 * 
	 * @param driver
	 * @param by
	 * @param time
	 * @return
	 */
	public static WebElement implicitWait(WebDriver driver,By by,int time) { 
		WebElement ele = null;
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		try {
			ele = driver.findElement(by);
		} catch (NoSuchElementException e) {
			KSU.error("Element not found by implicit wait!");
			e.printStackTrace();
		} 
		return ele;	
	}

}
