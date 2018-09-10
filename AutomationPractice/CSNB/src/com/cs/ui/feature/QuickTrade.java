package com.cs.ui.feature;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cs.ks.util.KSU;
import com.cs.ui.util.WebActionUtil;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebDriverUtil;
import com.cs.ui.util.WebInfoUtil;
import com.cs.ui.util.WebUtil;


public class QuickTrade extends WebDriverBase{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	private static String tradeAmt;

	/**
	 * 
	 * @return
	 */
	public String getAmt() {
		return tradeAmt;
	}

	/**
	 * 
	 * @param inTrade
	 * @param inAmt
	 * @param inFor
	 * @throws Exception
	 */
	public static void quickTrade(String inTrade, double inAmt, String inFor) throws Exception
	{
		
		BigDecimal inAmtBD = WebUtil.doubleToBigDecimal(inAmt);
		String amountStr = WebUtil.bigDecimalToStr(inAmtBD);
		
		KSU.msgOut("Starting Quick Trade:");
		KSU.msgOut("Selling: "+inTrade+", Amount: "+inAmt+", for: "+inFor);
		
		WebActionUtil.selectDropdown("Quick Trade", "Trade", inTrade);
		String amountXpath = "//input[@placeholder='"+WebInfoUtil.TEXT_ENTER_YOUR_AMOUNT+"']";
		WebElement amount = WebDriverUtil.isElementPresent(theDriver, By.xpath(amountXpath), 30);
		WebActionUtil.clickToSendKeys(amount, theDriver, amountStr);
		
		WebActionUtil.selectDropdown("Quick Trade", "For", inFor);
		String getQuoteXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_GET_A_QUOTE+"']";
		WebElement getQuote = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteXpath), 30);
		WebActionUtil.click(getQuote, theDriver);
		
		String makeTradeXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_MAKE_THIS_TRADE+"']";
		WebElement makeTrade = WebDriverUtil.isElementPresent(theDriver, By.xpath(makeTradeXpath), 30);
		WebActionUtil.click(makeTrade, theDriver);
		
		KSU.msgOut("Quick Trade - Done.");
	}
	
	/** 
	 * 
	 * @param inTrade
	 * @param inPercent
	 * @param inFor
	 * @throws Exception
	 */
	public static void quickTradeByPercent(String inTrade, String inPercent, String inFor) throws Exception
	{
		KSU.msgOut("Starting Quick Trade:");
		KSU.msgOut("Selling: "+inTrade+", Percent: "+inPercent+", for: "+inFor);
		
		WebActionUtil.selectDropdown("Quick Trade", "Trade", inTrade);

		String percentBtnXpath = "//div[contains(@class,'styles__tradeBox__')]/div/form[contains(@class,'styles__tradeControls__')]/div[3]/div";
		List<WebElement> percentBtnElm = WebDriverUtil.areElementsPresent(theDriver, By.xpath(percentBtnXpath), 30);
		int numOfBtn =  percentBtnElm.size();
		
		for (int i=1; i<=numOfBtn; i++) {
			String percentXpath = percentBtnXpath+"["+i+"]/button/div";
			WebElement percent = WebDriverUtil.isElementPresent(theDriver, By.xpath(percentXpath), 30);
			KSU.out(percent.getText());
			
			if (percent.getText().equals(inPercent)) {
				WebActionUtil.click(percent, theDriver);
				break;
			}
		}
		
		WebActionUtil.selectDropdown("Quick Trade", "For", inFor);
		String getQuoteXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_GET_A_QUOTE+"']";
		WebElement getQuote = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteXpath), 30);
		WebActionUtil.click(getQuote, theDriver);
		
		String makeTradeXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_MAKE_THIS_TRADE+"']";
		WebElement makeTrade = WebDriverUtil.isElementPresent(theDriver, By.xpath(makeTradeXpath), 30);
		WebActionUtil.click(makeTrade, theDriver);
		
		KSU.msgOut("Quick Trade - Done.");
	}
	

	/**
	 * 
	 * @param inTrade
	 * @param inAmt
	 * @param inFor
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal quickTradeReturnQuote(String inTrade, double inAmt, String inFor) throws Exception
	{
		BigDecimal rval, inAmtBD;
		inAmtBD = WebUtil.doubleToBigDecimal(inAmt);
		String amountStr = WebUtil.bigDecimalToStr(inAmtBD);
		
		KSU.msgOut("Starting Quick Trade:");
		KSU.msgOut("Selling: "+inTrade+", Amount: "+amountStr+", for: "+inFor);
			
		WebActionUtil.selectDropdown("Quick Trade", "Trade", inTrade);
		String amountXpath = "//input[@placeholder='"+WebInfoUtil.TEXT_ENTER_YOUR_AMOUNT+"']";
		WebElement amount = WebDriverUtil.isElementPresent(theDriver, By.xpath(amountXpath), 30);
		WebActionUtil.clickToSendKeys(amount, theDriver, amountStr);
		
		WebActionUtil.selectDropdown("Quick Trade", "For", inFor);
		
		String getQuoteXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_GET_A_QUOTE+"']";
		WebElement getQuote = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteXpath), 30);
		WebActionUtil.click(getQuote, theDriver);
		
		String getQuoteAmtXpath = "//div[contains(@class,'styles__tradeAmount__')]/div[contains(@class,'styles__amount__')]";
		WebElement getQuoteAmt = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteAmtXpath), 120);
		
        String quotedAmount = getQuoteAmt.getText();
        String quotedAmountPlain = quotedAmount.replace(",","");
        rval = new BigDecimal(quotedAmountPlain);
		
		String makeTradeXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_MAKE_THIS_TRADE+"']";
        WebElement makeTrade = WebDriverUtil.isElementPresent(theDriver, By.xpath(makeTradeXpath), 30);
		WebActionUtil.click(makeTrade, theDriver);
		
		KSU.out("Quoted Amount is: "+rval);
		KSU.msgOut("Quick Trade - Done.");	
		return rval;
	}	
	
	/**
	 * 
	 * @param inTrade
	 * @param inPercent
	 * @param inFor
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal quickTradeByPercentReQuote(String inTrade, String inPercent, String inFor) throws Exception
	{
		BigDecimal rval;
		
		KSU.msgOut("Starting Quick Trade:");
		KSU.msgOut("Selling: "+inTrade+", Percent: "+inPercent+", for: "+inFor);
			
		WebActionUtil.selectDropdown("Quick Trade", "Trade", inTrade);

		String percentBtnXpath = "//div[contains(@class,'styles__tradeBox__')]/div/form[contains(@class,'styles__tradeControls__')]/div[3]/div";
		List<WebElement> percentBtnElm = WebDriverUtil.areElementsPresent(theDriver, By.xpath(percentBtnXpath), 30);
		int numOfBtn =  percentBtnElm.size();
		
		for (int i=1; i<=numOfBtn; i++) {
			String percentXpath = percentBtnXpath+"["+i+"]/button/div";
			WebElement percent = WebDriverUtil.isElementPresent(theDriver, By.xpath(percentXpath), 30);
			KSU.out(percent.getText());
			
			if (percent.getText().equals(inPercent)) {
				WebActionUtil.click(percent, theDriver);
				break;
			}
		}
		
		verifyPercentAmt(inPercent, inTrade);		
		
		WebActionUtil.selectDropdown("Quick Trade", "For", inFor);
		
		String getQuoteXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_GET_A_QUOTE+"']";
		WebElement getQuote = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteXpath), 30);
		WebActionUtil.click(getQuote, theDriver);
		
		String getQuoteAmtXpath = "//div[contains(@class,'styles__tradeAmount__')]/div[contains(@class,'styles__amount__')]";
		WebElement getQuoteAmt = WebDriverUtil.isElementPresent(theDriver, By.xpath(getQuoteAmtXpath), 30);
		
        String quotedAmount = getQuoteAmt.getText();
        String quotedAmount1 = quotedAmount.replace(",",  "");
        rval = new BigDecimal(quotedAmount1);
		
		String makeTradeXpath = "//button[@type='button']/div[contains(@class,'styles__textWrapper__')]/span[text()='"+WebInfoUtil.BUTTON_MAKE_THIS_TRADE+"']";
        WebElement makeTrade = WebDriverUtil.isElementPresent(theDriver, By.xpath(makeTradeXpath), 30);
		WebActionUtil.click(makeTrade, theDriver);
		
		KSU.out("Quoted Amount is: "+rval);
		KSU.msgOut("Quick Trade - Done.");	
		return rval;
	}
	
	/**
	 * 
	 * @param inPercent
	 * @param inTrade
	 * @throws Exception
	 */
	public static void verifyPercentAmt(String inPercent, String inTrade) throws Exception
	{		
		BigDecimal amountBD, expAmtBD, percentBD, inHoldingsBD, expAmtRoundedBD = null;
		String balanceStr;
		percentBD = WebUtil.percentStrToBigDecimal(inPercent);
		
		String balanceXpath = "//span[contains(@class, 'styles__balance__')]";
		WebElement balanceEle = WebDriverUtil.isElementPresent(theDriver, By.xpath(balanceXpath), 30);
		balanceStr = balanceEle.getText();
		inHoldingsBD = WebUtil.strToBigDecimal(balanceStr);
		
		expAmtBD = inHoldingsBD.multiply(percentBD);
				
		int numAmtBD = WebUtil.getNumberOfDecimalPlaces(expAmtBD);

		if (8 <= numAmtBD) {
			expAmtRoundedBD = expAmtBD.setScale(8, BigDecimal.ROUND_HALF_UP);
		} else if(2 < numAmtBD) {
			expAmtRoundedBD = expAmtBD.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			expAmtRoundedBD = expAmtBD;
		}
		
		KSU.msgOut("Starting verify the percentage amount: ");
		
		String amountXpath = "//input[@placeholder='"+WebInfoUtil.TEXT_ENTER_YOUR_AMOUNT+"']";
		WebElement amount = WebDriverUtil.isElementPresent(theDriver, By.xpath(amountXpath), 30);
		String amountStr = amount.getAttribute("value");
		amountBD = WebUtil.strToBigDecimal(amountStr);
		
		KSU.msgOut("Verifying "+inPercent+" for: "+inTrade+ " (" +balanceStr+ ") is: " +expAmtRoundedBD);
		Assert.assertEquals(amountBD,expAmtRoundedBD); 
		KSU.msgOut("Verify the percentage amount - Done.");
	}	
	
}


	

