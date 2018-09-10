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

public class C5503_CadLtc
extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeCadLtc() throws Exception
	{		
		KSU.testMsgOut("Test Case C5503: Dashboard: Quick Trade – CAD/LTC");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "CAD", tradeFor = "LTC";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double ltcQuant, cadVal;
		ltcQuant = Holdings.getHoldings("LTC", "Quantity");
		cadVal = Holdings.getHoldings("CAD", "CAD");
		
		
		//Quick Trade CAD to LTC
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedLtcQuant;
		double tradeAmt = 0.1;
		
		Holdings.verifyAvailBal(tradeAmt, cadVal, tradeSell);
			
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
		cadVal = cadVal - tradeAmt;
		quotedVal = quotedValBD.doubleValue();
		ltcQuant = ltcQuant + quotedVal;
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "CAD";
		Holdings.verifyHoldings(tradeSell, colName, cadVal);
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeFor, colName, roundedLtcQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade LTC to CAD
		tradeAmt = quotedVal;
		tradeSell = "LTC";
		tradeFor = "CAD";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		quotedPairedValBD = QuickTrade.quickTradeReturnQuote(tradeSell, tradeAmt, tradeFor);
		
		//Verify Ledger
		inVal = String.valueOf(quotedPairedValBD)+" "+tradeFor;
		Transactions.transactionsTab();
		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
		
		//Verify Holdings
		quotedPairedVal = quotedPairedValBD.doubleValue();
		cadVal = cadVal + quotedPairedVal;
		quotedVal = quotedValBD.doubleValue();
		ltcQuant = ltcQuant - quotedVal;		
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedLtcQuant);
		colName = "CAD";
		Holdings.verifyHoldings(tradeFor, colName, cadVal);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5503 (Done): Dashboard: Quick Trade – CAD/LTC");
		//Thread.sleep(3000);
	}
	
}
