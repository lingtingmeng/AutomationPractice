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

public class C5525_BtcDash extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeBtcDash() throws Exception
	{		
		KSU.testMsgOut("Test Case C5525: Dashboard: Quick Trade – BTC/DASH");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "BTC", tradeFor = "DASH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double btcQuant, dashQuant;
		btcQuant = Holdings.getHoldings("BTC", "Quantity");
		dashQuant = Holdings.getHoldings("DASH", "Quantity");
		
		//Quick Trade BTC to DASH
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedBtcQuant, roundedDashQuant;
		double tradeAmt = 0.001/100;
		
		Holdings.verifyAvailBal(tradeAmt, btcQuant, tradeSell);
		
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
		btcQuant = btcQuant - tradeAmt;
		quotedVal = quotedValBD.doubleValue();
		dashQuant = dashQuant + quotedVal;
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedDashQuant = Math.round(dashQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedDashQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade DASH to BTC
		tradeAmt = quotedVal;
		tradeSell = "DASH";
		tradeFor = "BTC";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		quotedPairedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
		
		//Verify Ledger
		inVal = String.valueOf(quotedPairedValBD)+" "+tradeFor;
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
		
		//Verify Holdings
		quotedPairedVal = quotedPairedValBD.doubleValue();
		quotedVal = quotedValBD.doubleValue();
		dashQuant = dashQuant - tradeAmt;
		btcQuant = btcQuant + quotedPairedVal;		
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedDashQuant = Math.round(dashQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedDashQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBtcQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5525 (Done): Dashboard: Quick Trade – BTC/DASH");
		//Thread.sleep(3000);
	}
	
}
