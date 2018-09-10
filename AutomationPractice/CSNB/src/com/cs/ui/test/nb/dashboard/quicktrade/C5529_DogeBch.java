package com.cs.ui.test.nb.dashboard.quicktrade;

import java.math.BigDecimal;
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

public class C5529_DogeBch extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeDogeBch() throws Exception
	{		
		KSU.testMsgOut("Test Case C5529: Dashboard: Quick Trade – DOGE/BCH");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "DOGE", tradeFor = "BCH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double dogeQuant, bchQuant;
		dogeQuant = Holdings.getHoldings("DOGE", "Quantity");
		bchQuant = Holdings.getHoldings("BCH", "Quantity");
		
		//Quick Trade DOGE to BCH
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedDogeQuant, roundedBchQuant;
		double tradeAmt = 10;
		
		Holdings.verifyAvailBal(tradeAmt, dogeQuant, tradeSell);
		
		quotedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
		
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
		dogeQuant = dogeQuant - tradeAmt;
		quotedVal = quotedValBD.doubleValue();
		bchQuant = bchQuant + quotedVal;
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;
		roundedBchQuant = Math.round(bchQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedDogeQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBchQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade BCH to DOGE
		tradeAmt = quotedVal;
		tradeSell = "BCH";
		tradeFor = "DOGE";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		quotedPairedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
		
		//Verify Ledger
		inVal = String.valueOf(quotedPairedValBD)+" "+tradeFor;
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
		
		//Verify Holdings
		quotedPairedVal = quotedPairedValBD.doubleValue();
		quotedVal = quotedValBD.doubleValue();
		bchQuant = bchQuant - tradeAmt;
		dogeQuant = dogeQuant + quotedPairedVal;		
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;
		roundedBchQuant = Math.round(bchQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBchQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedDogeQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5529 (Done): Dashboard: Quick Trade – DOGE/BCH");
		//Thread.sleep(3000);
	}
	
}
