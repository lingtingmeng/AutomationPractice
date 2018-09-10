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

public class C5519_UsdDash_100Percent extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeUsdDash_100Percent() throws Exception
	{		
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		KSU.testMsgOut("Test Case C5519: Dashboard: Quick Trade – USD/DASH (100%)");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "USD", tradeFor = "DASH";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double dashQuant, usdQuant;
		
		usdQuant = Holdings.getHoldings("USD", "Quantity");
		dashQuant = Holdings.getHoldings("DASH", "Quantity");
		
		//Quick Trade USD to DASH: 100%
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedDashQuant;
		String tradePercent = "100%";
			
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
		dashQuant = dashQuant + quotedVal;
		roundedDashQuant = Math.round(dashQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, usdQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedDashQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade DASH to USD
		double tradeAmt = quotedVal;
		tradeSell = "DASH";
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
		dashQuant = dashQuant - quotedVal;		
		roundedDashQuant = Math.round(dashQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedDashQuant);
		Holdings.verifyHoldings(tradeFor, colName, usdQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5519 (DONE): Dashboard: Quick Trade – USD/DASH (100%)");
		//Thread.sleep(3000);
	}
	
}
