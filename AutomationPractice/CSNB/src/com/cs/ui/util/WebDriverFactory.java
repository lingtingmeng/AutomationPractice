package com.cs.ui.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class WebDriverFactory {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	public enum BrowserType {
		FIREFOX,
		CHROME,
		IE,
		SAFARI
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static WebDriver getDriver(BrowserType type){
		WebDriver driver = null;
		switch(type){
			case SAFARI:
				driver = new SafariDriver();
				break;
			case IE:
				driver = new InternetExplorerDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				if (WebOsUtil.isMac() == true) {
					System.setProperty("webdriver.chrome.driver", "./driver/mac/chromedriver");
					
				} else if (WebOsUtil.isWindows() == true) {
					System.setProperty("webdriver.chrome.driver", ".\\driver\\win\\chromedriver.exe");
					
				} else if (WebOsUtil.isLinux() == true) {
					System.setProperty("webdriver.chrome.driver", "./driver/linux/chromedriver");
					
					
				}
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				driver = new ChromeDriver(options);
				break;
			default:
				driver = new FirefoxDriver();
				break;
		}
		return driver;
		
	}

}
