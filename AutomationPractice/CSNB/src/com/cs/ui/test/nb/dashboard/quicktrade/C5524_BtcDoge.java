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

public class C5524_BtcDoge extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeBtcDoge() throws Exception
	{		
		KSU.testMsgOut("Test Case C5524: Dashboard: Quick Trade – BTC/DOGE");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "BTC", tradeFor = "DOGE";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double btcQuant, dogeQuant;
		btcQuant = Holdings.getHoldings("BTC", "Quantity");
		dogeQuant = Holdings.getHoldings("DOGE", "Quantity");
		
		//Quick Trade BTC to DOGE
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedBtcQuant, roundedDogeQuant;
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
		dogeQuant = dogeQuant + quotedVal;
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedDogeQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade DOGE to BTC
		tradeAmt = quotedVal;
		tradeSell = "DOGE";
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
		dogeQuant = dogeQuant - tradeAmt;
		btcQuant = btcQuant + quotedPairedVal;		
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedDogeQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBtcQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5524 (Done): Dashboard: Quick Trade – BTC/DOGE");
		//Thread.sleep(3000);
	}
	
}
