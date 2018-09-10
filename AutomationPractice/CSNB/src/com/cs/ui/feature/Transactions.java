package com.cs.ui.feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;


public class Transactions extends WebDriverBase{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	private static double resultDou, valDou;
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void transactionsTab() throws Exception
	{
		Thread.sleep(3000);
		String xpathTransactions = WebInfoUtil.TAB_TRANSACTIONS;
		WebElement transactions = WebDriverUtil.isElementPresent(theDriver, By.linkText(xpathTransactions), 30);
		WebActionUtil.click(transactions, theDriver);
	}
	
	/**
	 * 
	 * @param inCurrency
	 * @param inTab
	 * @param inYear
	 * @throws Exception
	 */
	public static void export(String inCurrency, String inTab, String inYear) throws Exception
	{
		KSU.msgOut("Starting Export Ledger: [Currency : "+inCurrency+"; Year : "+inYear+"; Tab : "+inTab+"]");
		
		if(inCurrency!= null) {
			String baseXpath = "//div[contains(@class,'styles__currencySelector__')]/div[contains(@class,'styles__options__')]/div";
			int numberOfSiblingsCurrencies = theDriver.findElements(By.xpath(baseXpath)).size(); //15
			int findCurrencyRowNum=0;
			String currencyXpath = null;
			for(findCurrencyRowNum = 1 ; findCurrencyRowNum <= numberOfSiblingsCurrencies; findCurrencyRowNum++) {
				currencyXpath = baseXpath+"["+ findCurrencyRowNum +"]/div/span";
				String findRowNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(currencyXpath), 60).getText();
				if(findRowNumber.equals(inCurrency)) {
					break;
				}	
			}
			
			if (findCurrencyRowNum > numberOfSiblingsCurrencies){
				KSU.error("Can not find Currency = "+ inCurrency +" !");
				theDriver.quit();
				throw new Exception();
			} else {
				WebElement viewCurrency = WebDriverUtil.isElementPresent(theDriver, By.xpath(currencyXpath), 60);
				WebActionUtil.click(viewCurrency, theDriver);
				Thread.sleep(3000);
			}
		}
		
		if(inYear!= null && inYear!=WebInfoUtil.DROPDOWN_ALL) {
			WebActionUtil.inputDropdown(WebInfoUtil.PANEL_TRANSACTIONS, WebInfoUtil.DROPDOWN_ALL, inYear);	
		}
		
		if(inTab!= null) {
			String tabXpath = "//div[contains(@class,'tyles__ledger__')]/div[contains(@class,'styles__categorySelector__')]/div";
			int numberOfSiblingsTab = theDriver.findElements(By.xpath(tabXpath)).size(); //3
			int findTabNum = 0;
			String tabColumnXpath = null;
			for(findTabNum = 1 ; findTabNum <= numberOfSiblingsTab; findTabNum++) {
				tabColumnXpath = tabXpath+"["+ findTabNum +"]";
				String tabColumn = WebDriverUtil.isElementPresent(theDriver, By.xpath(tabColumnXpath), 60).getText();
				if(tabColumn.equals(inTab)) {
					break;
				}	
			}
			
			if (findTabNum > numberOfSiblingsTab){
				KSU.error("Can not find Ledger tab = "+ inTab +" !");
				theDriver.quit();
				throw new Exception();
			} else {
				WebElement viewTab = WebDriverUtil.isElementPresent(theDriver, By.xpath(tabColumnXpath), 60);
				WebActionUtil.click(viewTab, theDriver);
				Thread.sleep(3000);
			}
		}
		
		
		String exportExcelXpath = "//div[contains(@class,'styles__csvExport_')]/div/span[text()='"+WebInfoUtil.BUTTON_EXPORT_TO_EXCEL+"']";
		WebElement export = WebDriverUtil.isElementPresent(theDriver, By.xpath(exportExcelXpath), 60);
		WebActionUtil.click(export, theDriver);
		
		KSU.msgOut("Export Ledger - Done.");
	}

	
	public static void verifyExport(String inCurrency, String inTab, String inYear) throws Exception
	{
		//POC: verify some cells in excel/libreOffice, ref: #702
	}
	
	
	/**
	 * 
	 * @param inCurrency
	 * @param inYear
	 * @param inTab
	 * @param inColName
	 * @param inRow
	 * @param inVal
	 * @throws Exception
	 */
	public static void verifyLedger(String inCurrency, String inYear, String inTab, String inColName, int inRow, String inVal) throws Exception
	{
		KSU.msgOut("Starting Verify Ledger: [Currency : "+inCurrency+"; Year : "+inYear+"; Tab : "+inTab+"; Column : "+inColName+"; Row# : "+inRow+"; Expected Value : "+inVal+"]");
		//Thread.sleep(3000);
		
		if(inCurrency!= null) {
			String baseXpath = "//div[contains(@class,'styles__currencySelector__')]/div[contains(@class,'styles__options__')]/div";
			int numberOfSiblingsCurrencies = theDriver.findElements(By.xpath(baseXpath)).size(); //15
			int findCurrencyRowNum=0;
			String currencyXpath = null;
			String currenySelection = "//div[contains(@class,'styles__currentSelection__')]/div/h2";
			
			for(findCurrencyRowNum = 1 ; findCurrencyRowNum <= numberOfSiblingsCurrencies; findCurrencyRowNum++) {
				currencyXpath = baseXpath+"["+ findCurrencyRowNum +"]/div/img";
				WebElement viewCurrency = WebDriverUtil.isElementPresent(theDriver, By.xpath(currencyXpath), 60);
				WebActionUtil.click(viewCurrency, theDriver);
				Thread.sleep(3000);
				
				String findRowNumber = WebDriverUtil.isElementPresent(theDriver, By.xpath(currenySelection), 60).getText();
				if(findRowNumber.equals(inCurrency)) {
					break;
				}	
			}
			
			if (findCurrencyRowNum > numberOfSiblingsCurrencies){
				KSU.error("Can not find Currency = "+ inCurrency +" !");
				theDriver.quit();
				throw new Exception();
			} 
		}
		
		if(inYear!= null && inYear!=WebInfoUtil.DROPDOWN_ALL) {
			WebActionUtil.inputDropdown(WebInfoUtil.PANEL_TRANSACTIONS, WebInfoUtil.DROPDOWN_ALL, inYear);	
		}
		
		if(inTab!= null) {
			String tabXpath = "//div[contains(@class,'tyles__ledger__')]/div[contains(@class,'styles__categorySelector__')]/div";
			int numberOfSiblingsTab = theDriver.findElements(By.xpath(tabXpath)).size(); //3
			int findTabNum = 0;
			String tabColumnXpath = null;
			for(findTabNum = 1 ; findTabNum <= numberOfSiblingsTab; findTabNum++) {
				tabColumnXpath = tabXpath+"["+ findTabNum +"]";
				WebElement tabColumnEle = WebDriverUtil.isElementPresent(theDriver, By.xpath(tabColumnXpath), 60);
				String tabColumn = tabColumnEle.getText();
				if(tabColumn.equals(inTab)) {
					break;
				}	
			}
			
			if (findTabNum > numberOfSiblingsTab){
				KSU.error("Can not find Ledger tab = "+ inTab +" !");
				theDriver.quit();
				throw new Exception();
			} else {
				WebElement viewTab = WebDriverUtil.isElementPresent(theDriver, By.xpath(tabColumnXpath), 60);
				WebActionUtil.click(viewTab, theDriver);
				Thread.sleep(3000);
			}
		}
		
		KSU.out("Waiting for Ledger to update...");
		Thread.sleep(30000);		
		
		int colNumber=0;
		if(inColName!=null) {
			String colXpath = "//div[contains(@class,'styles__subheader__')]/div";
			int numberOfSiblingsCol = theDriver.findElements(By.xpath(colXpath)).size();
			int findColNum = 0;
			String columnXpath = null;
			for(findColNum = 1 ; findColNum <= numberOfSiblingsCol; findColNum++) {
				columnXpath = colXpath+"["+ findColNum +"]/span";
				String columnStr = WebDriverUtil.isElementPresent(theDriver, By.xpath(columnXpath), 60).getText();
				if(columnStr.equals(inColName)) {
					colNumber = findColNum;
					break;
				}	
			}
			if (findColNum > numberOfSiblingsCol){
				KSU.error("Can not find column name = "+ inColName +" !");
				theDriver.quit();
				throw new Exception();
			} 			
		}
		
		String subHeaderXpath = "//div[contains(@class,'styles__subheader__')]";
		WebElement subHeader = WebDriverUtil.isElementPresent(theDriver, By.xpath(subHeaderXpath), 30);
		
		String ledgerValXpath = "following-sibling::*["+inRow+"]/div/div["+colNumber+"]/span";
		WebElement ledgerVal = subHeader.findElement(By.xpath(ledgerValXpath));
		String ledgerValStr =  ledgerVal.getText();

		String[] splitedledgerValStr = ledgerValStr.split("\\s+");
		
		String valStr = splitedledgerValStr[0];
		String numStr = valStr.replaceAll("[$,]","");
		String coinStr = null;
		if (inTab == WebInfoUtil.TAB_QUICKTRADE){
			coinStr = splitedledgerValStr[1];
			resultDou = Double.parseDouble(numStr);
			String[] splitedInValStr = inVal.split("\\s+");
			
			String inValStr = splitedInValStr[0];
			valDou = Double.parseDouble(inValStr);
			
			KSU.out("Actual Value - "+resultDou+" "+coinStr+". Apply 10% acceptable range.");
			Assert.assertTrue(resultDou > 0.9*valDou && resultDou < 1.1*valDou);   //10% range thresholds applied.
		} else if (inTab == WebInfoUtil.TAB_FUND_WITHDRAW) {
			KSU.out("Actual Value in the Ledger - "+valStr);
			Assert.assertEquals(valStr, inVal);
		}
					
		KSU.msgOut("Verify Ledger - Done.");
	}
	
}


	

