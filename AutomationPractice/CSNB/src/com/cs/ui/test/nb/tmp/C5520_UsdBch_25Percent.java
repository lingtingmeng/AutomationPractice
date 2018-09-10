package com.cs.ui.test.nb.tmp;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.cs.ks.util.KSU;
import com.cs.ui.feature.Dashboard;
import com.cs.ui.feature.Holdings;
import com.cs.ui.feature.Login;
import com.cs.ui.feature.Logout;
import com.cs.ui.feature.QuickTrade;
import com.cs.ui.feature.Transactions;
import com.cs.ui.util.WebDriverBase;
import com.cs.ui.util.WebUtil;

public class C5520_UsdBch_25Percent extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeUsdBch_25Percent() throws Exception
	{		
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		KSU.testMsgOut("Test Case C5520: Dashboard: Quick Trade – USD/BCH (25%)");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "USD", tradeFor = "BCH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double bchQuant, usdQuant;
		
		usdQuant = Holdings.getHoldings("USD", "Quantity");
		bchQuant = Holdings.getHoldings("BCH", "Quantity");
		
		//Quick Trade USD to BCH: 25%
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedBchQuant;
		String tradePercent = "25%";
			
		quotedValBD = QuickTrade.quickTradeByPercentReQuote(tradeSell, tradePercent, tradeFor);
		
		//Verify Ledger
		String yearStr,tabStr,colStr;
		yearStr = "ALL";
		tabStr = "Quick Trade";
		colStr = "TO";
		inVal = String.valueOf(quotedValBD)+" "+tradeFor;
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
		
		//Verify Holdings
		String colName;		
		double usdQuantDou =  usdQuant * WebUtil.percentStrToDouble(tradePercent);
		usdQuantDou = Double.valueOf(df.format(usdQuantDou));
		usdQuant = usdQuant - usdQuantDou;
		
		quotedVal = quotedValBD.doubleValue();
		bchQuant = bchQuant + quotedVal;
		roundedBchQuant = Math.round(bchQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, usdQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBchQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade BCH to USD
		double tradeAmt = quotedVal;
		tradeSell = "BCH";
		tradeFor = "USD";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		quotedPairedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
		
		//Verify Ledger
		inVal = String.valueOf(quotedPairedValBD)+" "+tradeFor;
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
		
		//Verify Holdings
		quotedPairedVal = quotedPairedValBD.doubleValue();
		usdQuant = usdQuant + quotedPairedVal;
		quotedVal = quotedValBD.doubleValue();
		bchQuant = bchQuant - quotedVal;		
		roundedBchQuant = Math.round(bchQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBchQuant);
		Holdings.verifyHoldings(tradeFor, colName, usdQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5520 (DONE): Dashboard: Quick Trade – USD/BCH (25%)");
		//Thread.sleep(3000);
	}
	
}
