package com.cs.ui.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cs.ks.util.KSU;


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
             type = WebDriverFactory.BrowserType.CHROME;
         } else if (WebOsUtil.isLinux() == true) {
     	 	//type = WebDriverFactory.BrowserType.FIREFOX;
            type = WebDriverFactory.BrowserType.CHROME;
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
	 * @param by
	 * @param driver
	 * @return
	 */
	public static WebElement getStaleElem(By by, WebDriver driver) {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, WebInfoUtil.WAIT_TIME_60);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        return driver.findElement(by);
	    } catch (StaleElementReferenceException e) {
	    		KSU.warn("Attempting to recover from StaleElementReferenceException ...");
	        return getStaleElem(by, driver);
	    } catch (NoSuchElementException ele) {
	    		KSU.warn("Attempting to recover from StaleElementReferenceException ...");
	        return getStaleElem(by, driver);
	    }
	}
	
	
	/**
	 * 
	 * @param by
	 * @param driver
	 * @return
	 */
	public static WebElement getStaleClickableElem(By by, WebDriver driver) {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, WebInfoUtil.WAIT_TIME_60);
	        wait.until(ExpectedConditions.elementToBeClickable(by));

	        return driver.findElement(by);
	    } catch (StaleElementReferenceException e) {
	    		KSU.warn("Attempting to recover from StaleElementReferenceException ...");
	        return getStaleElem(by, driver);
	    } catch (NoSuchElementException ele) {
	    		KSU.warn("Attempting to recover from StaleElementReferenceException ...");
	        return getStaleElem(by, driver);
	    }
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
	
	/**
	 * 
	 * @param by
	 * @return
	 */
	public boolean validateElementVisible(final By by) {
		WebDriver theDriver = WebDriverUtil.getDriver();
		try{
			new WebDriverWait(theDriver, WebInfoUtil.WAIT_TIME_60).until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(RuntimeException e){
			return false;
		}
		return true;
	}
	
	/**
	With Java 7, even a single multicatch block would be sufficient:
	WebElement getStaleElem(By by, WebDriver driver) {
	    try {
	        return driver.findElement(by);
	    } catch (StaleElementReferenceException | NoSuchElementException e) {
	        System.out.println("Attempting to recover from " + e.getClass().getSimpleName() + "...");
	        return getStaleElem(by, driver);
	    }
	}
	**/
	
	

}
