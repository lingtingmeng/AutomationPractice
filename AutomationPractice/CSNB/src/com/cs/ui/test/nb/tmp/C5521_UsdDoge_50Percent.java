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

public class C5521_UsdDoge_50Percent extends WebDriverBase{
	
	@Test
	
	/**
	 * 
	 * @throws Exception
	 */
	public void quickTradeUsdDoge_50Percent() throws Exception
	{		
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		KSU.testMsgOut("Test Case C5521: Dashboard: Quick Trade – USD/DOGE (50%)");
		Login.login();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Dashboard
		Dashboard.dashboardTab();
		
		//Get Current Holdings
		String inVal, tradeSell = "USD", tradeFor = "DOGE";
		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
		double dogeQuant, usdQuant;
		
		usdQuant = Holdings.getHoldings("USD", "Quantity");
		dogeQuant = Holdings.getHoldings("DOGE", "Quantity");
		
		//Quick Trade USD to DOGE: 50%
		BigDecimal quotedValBD, quotedPairedValBD;
		double quotedVal, quotedPairedVal, roundedDogeQuant;
		String tradePercent = "50%";
			
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
		dogeQuant = dogeQuant + quotedVal;
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, usdQuant);
		Holdings.verifyHoldings(tradeFor, colName, roundedDogeQuant);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//Quick Trade DOGE to USD
		double tradeAmt = quotedVal;
		tradeSell = "DOGE";
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
		dogeQuant = dogeQuant - quotedVal;		
		roundedDogeQuant = Math.round(dogeQuant*100000000)/100000000.0d;
		
		Dashboard.dashboardTab();
		colName = "Quantity";
		Holdings.verifyHoldings(tradeSell, colName, roundedDogeQuant);
		Holdings.verifyHoldings(tradeFor, colName, usdQuant);
		
		//LogOut
		Logout.logout();
		KSU.testMsgOut("Test Case C5521 (DONE): Dashboard: Quick Trade – USD/DOGE (50%)");
		//Thread.sleep(3000);
	}
	
}
