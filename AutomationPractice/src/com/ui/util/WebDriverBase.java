package com.ui.util;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.ks.util.KSU;


public class WebDriverBase {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	public static WebDriver theDriver;
	private static long start, runTime;
	
	@BeforeTest
	public void initialiseBrowser() {
		theDriver = WebDriverUtil.getDriver();	
	}

	@AfterTest
	public void closeBrowser() {
		theDriver.close();
	}
	
    @BeforeClass(alwaysRun = true)
    public void beforeCls() {
        start = System.currentTimeMillis();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
    		runTime =  (System.currentTimeMillis() - start) / 1000;
        KSU.out("Duration: [Class Name : " + this.getClass().getName()
                + ", Running Time : "+runTime+" seconds.]");
    }
}
