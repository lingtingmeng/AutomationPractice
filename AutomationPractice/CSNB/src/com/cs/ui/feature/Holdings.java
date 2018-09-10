package com.cs.ui.feature;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;


public class Holdings extends WebDriverBase{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	private static String holdingsStr = null;
	private static double holdingsDou;

	/**
	 * 
	 * @param inCurrency
	 * @param inColName
	 * @return
	 * @throws Exception
	 */
	public static double getHoldings(String inCurrency, String inColName) throws Exception
	{
		KSU.msgOut("Getting Holdings for " +inCurrency+ ", Column: "+inColName);
		Thread.sleep(3000);
		
		//1. get column#: colNum
		String getColumn = "//div[contains(@class,'styles__stats__')]/div[contains(@class,'styles__headings__')]/div";
		int numberOfSiblingsColumns = theDriver.findElements(By.xpath(getColumn)).size();
		int colNum=0;
		String colXpath = null;
		for(colNum = 1 ; colNum <= numberOfSiblingsColumns; colNum++) {
			colXpath = getColumn+"["+ colNum +"]";
			String findColNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(colXpath), 60).getText();
			if(findColNumber.equals(inColName)) {
				break;
			}	
		}
		
		//2. get row# for checking 1st column name with inCurrency--column
		String getRow = "//div[contains(@class,'styles__stats__')]/div";
		int numberOfSiblingsRows = theDriver.findElements(By.xpath(getRow)).size(); //exclude the header
		int rowNum=0;
		String rowXpath = null;
		for(rowNum = 2 ; rowNum <= numberOfSiblingsRows; rowNum++) {
			rowXpath = getRow+"["+ rowNum +"]/div";
			String findColNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(rowXpath), 60).getText();
			if(findColNumber.equals(inCurrency)) {
				break;
			}
		}
		
		if(numberOfSiblingsRows < rowNum) {
			KSU.msgOut("Holdings for " +inCurrency+ " is not available! ");	
		} else {
			String getVal = getRow+"["+ rowNum +"]/div["+colNum+"]";
			holdingsStr = WebDriverUtil.isElementPresent(theDriver, By.xpath(getVal), 60).getText();
			
			String resultStr = holdingsStr.replaceAll("[$,]","");
			holdingsDou = Double.parseDouble(resultStr);
			
			KSU.msgOut("Holdings for " +inCurrency+ " is: "+resultStr);	
		}
		return holdingsDou;
	}
	
	/**
	 * 
	 * @param inSort
	 * @throws Exception
	 */
	public static void sortHoldings(String inSort) throws Exception
	{
		KSU.msgOut("Sorting Holdings: " +inSort);
		WebActionUtil.inputDropdown(WebInfoUtil.PANEL_HOLDINGS, WebInfoUtil.DROPDOWN_HOLDINGS, inSort);	
		KSU.msgOut("Sorting Holdings - Done.");
	}
	
	/**
	 * 
	 * @param inTradeAmt
	 * @param inVal
	 * @param inTradeSell
	 * @throws IllegalArgumentException
	 */
    public static void verifyAvailBal(double inTradeAmt, double inVal, String inTradeSell) throws IllegalArgumentException{
        if(inTradeAmt > inVal){
        	throw new IllegalArgumentException("Available " + inTradeSell + " Amount: " + inVal + ", not enough " + inTradeSell + " amount "+inTradeAmt+" for trade!");
        }else {
        	KSU.info("Enough balance for trade.");
        }
    }
	
	/**
	 * 
	 * @param inCurrency
	 * @param inColName
	 * @param inVal
	 * @throws Exception
	 */
	public static void verifyHoldings(String inCurrency, String inColName, double inVal) throws Exception
	{
		//1. get column#: colNum
		KSU.msgOut("Verifying Holdings for " +inCurrency+ ", Column: "+inColName+ ", is: "+inVal);
		
		Thread.sleep(3000);
		
		String getColumn = "//div[contains(@class,'styles__stats__')]/div[contains(@class,'styles__headings__')]/div";
		int numberOfSiblingsColumns = theDriver.findElements(By.xpath(getColumn)).size();
		int colNum=0;
		String colXpath = null;
		for(colNum = 1 ; colNum <= numberOfSiblingsColumns; colNum++) {
			colXpath = getColumn+"["+ colNum +"]";
			String findColNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(colXpath), 60).getText();
			if(findColNumber.equals(inColName)) {
				break;
			}	
		}
		
		//2. get row# for checking 1st column name with inCurrency--column
		String getRow = "//div[contains(@class,'styles__stats__')]/div";
		int numberOfSiblingsRows = theDriver.findElements(By.xpath(getRow)).size(); //exclude the header
		int rowNum=0;
		String rowXpath = null;
		for(rowNum = 2 ; rowNum <= numberOfSiblingsRows; rowNum++) {
			rowXpath = getRow+"["+ rowNum +"]/div";
			String findColNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(rowXpath), 60).getText();
			if(findColNumber.equals(inCurrency)) {
				break;
			}
		}
		
		if(numberOfSiblingsRows < rowNum) {
			KSU.msgOut("Holdings for " +inCurrency+ " is not available! ");	
		} else {
			String getVal = getRow+"["+ rowNum +"]/div["+colNum+"]";
			holdingsStr = WebDriverUtil.isElementPresent(theDriver, By.xpath(getVal), 120).getText();
			
			String resultStr = holdingsStr.replaceAll("[$,]","");
			holdingsDou = Double.parseDouble(resultStr);
			
			KSU.out("Actual Holdings - "+holdingsDou+". Apply 10% acceptable range.");
			Assert.assertTrue(holdingsDou > 0.9*inVal && holdingsDou < 1.1*inVal); //10% range thresholds applied.
		}
		
		KSU.msgOut("Verifying Holdings for " +inCurrency+ " - Done.");
		
	}
}


	

