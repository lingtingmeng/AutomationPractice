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

public class C5508_BtcLtc extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeBtcLtc() throws Exception
	{		
		KSU.testMsgOut("Test Case C5508: Dashboard: Quick Trade – BTC/LTC");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "BTC", tradeFor = "LTC";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double btcQuant, ltcQuant;
		btcQuant = Holdings.getHoldings("BTC", "Quantity");
		ltcQuant = Holdings.getHoldings("LTC", "Quantity");
		
		//Quick Trade BTC to LTC
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedBtcQuant, roundedLtcQuant;
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
		ltcQuant = ltcQuant + quotedVal;
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedLtcQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade LTC to BTC
		tradeAmt = quotedVal;
		tradeSell = "LTC";
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
		ltcQuant = ltcQuant - tradeAmt;
		btcQuant = btcQuant + quotedPairedVal;		
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedLtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBtcQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5508 (Done): Dashboard: Quick Trade – BTC/LTC");
		//Thread.sleep(3000);
	}
	
}
