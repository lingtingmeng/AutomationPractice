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

public class C5522_BtcEth extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeBtcEth() throws Exception
	{		
		KSU.testMsgOut("Test Case C5522: Dashboard: Quick Trade – BTC/ETH");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "BTC", tradeFor = "ETH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double btcQuant, ethQuant;
		btcQuant = Holdings.getHoldings("BTC", "Quantity");
		ethQuant = Holdings.getHoldings("ETH", "Quantity");
		
		//Quick Trade BTC to ETH
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedBtcQuant, roundedEthQuant;
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
		ethQuant = ethQuant + quotedVal;
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedEthQuant = Math.round(ethQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedBtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedEthQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade ETH to BTC
		tradeAmt = quotedVal;
		tradeSell = "ETH";
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
		ethQuant = ethQuant - tradeAmt;
		btcQuant = btcQuant + quotedPairedVal;		
		roundedBtcQuant = Math.round(btcQuant*100000000)/100000000.0d;
		roundedEthQuant = Math.round(ethQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedEthQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedBtcQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5520 (Done): Dashboard: Quick Trade – BTC/ETH");
		//Thread.sleep(3000);
	}
	
}
