package com.test;

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
import com.cs.ui.util.WebUtil;

public class DebugTestCaseVerifyLedger extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeCadBtc() throws Exception
	{		
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		

		//Verify Ledger
		String yearStr,tabStr,colStr,tradeSell, inVal;
		tradeSell = "CAD";
		yearStr = "ALL";
		tabStr = "Fund & Withdraw";
		colStr = "STATUS";
		inVal = "Processed";
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
//		
//		//Verify Holdings
//		String colName;
//		
//		cadVal = cadVal * (1 - WebUtil.percentStrToDouble(tradePercent));
//		
//		quotedVal = quotedValBD.doubleValue();
//		btcQuant = btcQuant + quotedVal;
//		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
//		
//		Dashboard.dashboardTab();
//		
//		colName = "CAD";
//		Holdings.verifyHoldings(tradeSell, colName, cadVal);
//		
//		colName = "Quantity";
//		Holdings.verifyHoldings(tradeFor, colName, roundedBtcQuant);
//		
//		//////////////////////////////////////////////////////////////////////////////////////////
//		//Quick Trade BTC to CAD
//		double tradeAmt = quotedVal;
//		tradeSell = "BTC";
//		tradeFor = "CAD";
//		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
//		quotedPairedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
//		
//		//Verify Ledger
//		inVal = String.valueOf(quotedPairedValBD)+" "+tradeFor;
//		Transactions.transactionsTab();
//		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
//		
//		//Verify Holdings
//		quotedPairedVal = quotedPairedValBD.doubleValue();
//		cadVal = cadVal + quotedPairedVal;
//		quotedVal = quotedValBD.doubleValue();
//		btcQuant = btcQuant - quotedVal;		
//		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
//		
//		Dashboard.dashboardTab();
//		colName = "Quantity";
//		Holdings.verifyHoldings(tradeSell, colName, roundedBtcQuant);
//		colName = "CAD";
//		Holdings.verifyHoldings(tradeFor, colName, cadVal);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5483 (Done): Dashboard: Quick Trade – CAD/BTC");
		Thread.sleep(3000);
	}
	
}
