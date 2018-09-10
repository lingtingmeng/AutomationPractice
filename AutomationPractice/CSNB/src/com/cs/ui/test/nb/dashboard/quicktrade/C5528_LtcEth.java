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

public class C5528_LtcEth extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeLtcEth() throws Exception
	{		
		KSU.testMsgOut("Test Case C5528: Dashboard: Quick Trade – LTC/ETH");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "LTC", tradeFor = "ETH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double ltcQuant, ethQuant;
		ltcQuant = Holdings.getHoldings("LTC", "Quantity");
		ethQuant = Holdings.getHoldings("ETH", "Quantity");
		
		//Quick Trade LTC to ETH
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedLtcQuant, roundedEthQuant;
		double tradeAmt = 0.001;

		Holdings.verifyAvailBal(tradeAmt, ltcQuant, tradeSell);
		
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
		ltcQuant = ltcQuant - tradeAmt;
		quotedVal = quotedValBD.doubleValue();
		ethQuant = ethQuant + quotedVal;
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;
		roundedEthQuant = Math.round(ethQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedLtcQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedEthQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade ETH to LTC
		tradeAmt = quotedVal;
		tradeSell = "ETH";
		tradeFor = "LTC";
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
		ltcQuant = ltcQuant + quotedPairedVal;		
		roundedLtcQuant = Math.round(ltcQuant*100000000)/100000000.0d;
		roundedEthQuant = Math.round(ethQuant*100000000)/100000000.0d;

		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedEthQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedLtcQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5528 (Done): Dashboard: Quick Trade – LTC/ETH");
		//Thread.sleep(3000);
	}
	
}
