//package com.test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.annotations.Test;
//
//import com.cs.ks.util.KSU;
//import com.cs.ui.feature.Dashboard;
////import com.cs.ui.feature.DropDown;
//import com.cs.ui.feature.Holdings;
//import com.cs.ui.feature.Login;
//import com.cs.ui.feature.Logout;
//import com.cs.ui.feature.QuickTrade;
//import com.cs.ui.feature.Transactions;
//import com.cs.ui.util.WebActionUtil;
//import com.cs.ui.util.WebDriverBase;
//import com.cs.ui.util.WebDriverUtil;
//import com.cs.ui.util.WebInfoUtil;
//import com.cs.ui.util.WebUtil;
//
//public class DebugTestCase_Forum extends WebDriverBase{
//	
//	@Test
//	
//	/**
//	 * 
//	 * @throws Exception
//	 */
//	/*public void quickTradeCadBtc() throws Exception
//	{		
//		KSU.testMsgOut("Test Case C5483: Dashboard: Quick Trade – CAD/BTC");
//		Login.login();
//		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		//Dashboard
//		Dashboard.dashboardTab();
//		
//		//Get Current Holdings
//		String inVal, tradeSell = "CAD", tradeFor = "BTC";
//		KSU.msgOut("Sell " +tradeSell+ ", Buy " +tradeFor+ ":");
//		double btcQuant, cadVal;
//		
//		cadVal = Holdings.getHoldings("CAD", "CAD");
//		btcQuant = Holdings.getHoldings("BTC", "Quantity");
//		
//		//Quick Trade CAD to BTC: 25%
//		BigDecimal quotedValBD, quotedPairedValBD;
//		double quotedVal, quotedPairedVal, roundedBtcQuant;
//		String tradePercent = "100%";
//			
//		quotedValBD = QuickTrade.quickTradeByPercentReQuote(tradeSell, tradePercent, tradeFor);
//		
//		//Verify Ledger
//		String yearStr,tabStr,colStr;
//		yearStr = "ALL";
//		tabStr = "Quick Trade";
//		colStr = "TO";
//		inVal = String.valueOf(quotedValBD)+" "+tradeFor;
//		Transactions.transactionsTab();
//		Transactions.verifyLedger(tradeSell, yearStr, tabStr, colStr, 1, inVal);
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
//		
//		//LogOut
//		Logout.logout();
//		KSU.testMsgOut("Test Case C5483 (Done): Dashboard: Quick Trade – CAD/BTC");
//		Thread.sleep(3000);
//	}*/
//	
//	public void SettingsEmailChange() throws Exception 
//	{
//		KSU.testMsgOut("Test Case C8884: Change Email Address");
//		//Login.login();
//	
//		theDriver.get("https://coinsquare.com");
//		Thread.sleep(5000);
//		theDriver.findElement(By.linkText("Sign in")).click();
//		theDriver.findElement(By.name("username")).clear();
//		theDriver.findElement(By.name("username")).sendKeys("cs_qaforum");
//		theDriver.findElement(By.name("password")).clear();
//		theDriver.findElement(By.name("password")).sendKeys("testing123");
//		theDriver.findElement(By.xpath("//button[@type='submit']")).click();
//		
//		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		//Open Settings
//		DropDown.settings();
//		
//		
//		theDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		//Get Current Email Address
//		String CurEmail;
//		String EmailXpath = "//form[contains(@class,'styles__userDetails__10Vpj')]/div[3]/div[2]/div[1]/fieldtext[1]";
//		CurEmail = theDriver.findElement(By.xpath(EmailXpath)).getText();
//		KSU.msgOut("The Current Email Address: "+ CurEmail);
//		
//		//Click 'Change Email' button
//		String EmailBtnXpath = "//form[@class='styles__userDetails__10Vpj']//div[3]//div[3]//button[1]" ;
//		theDriver.findElement(By.xpath(EmailBtnXpath)).click();
//		
//		//Enter values
//		theDriver.findElement(By.name("email")).clear();
//		theDriver.findElement(By.name("email")).sendKeys("cs.qatest.001@gmail.com");
//		String NewEmail = theDriver.findElement(By.name("email")).getText();
//		KSU.msgOut("New Email Entered");
//		
//		theDriver.findElement(By.name("password")).clear();
//		theDriver.findElement(By.name("password")).sendKeys("testing123");
//		KSU.msgOut("Password Entered");
//		
//		//Click Submit
//		theDriver.findElement(By.xpath("//button[@type='submit']")).click();
//		//KSU.msgOut("Submitted Email Change");
//		
//		//Verify Message
//				String getMessage = theDriver.findElement(By.xpath("//div[contains(@class,'style__modal')]//form/div[4]/p")).getText();
//				
//				if(getMessage.contains("Confirm")) {
//					KSU.msgOut("Submitted Email Change");
//				}
//				else {
//					KSU.msgOut(getMessage);
//				}
//		
//		//Click Cancel Button
//		theDriver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
//			
//		//Open Gmail in new tab
//		WebActionUtil.openGmail();
//		WebActionUtil.enterEmail("cs.qatest.001@gmail.com");
//		WebActionUtil.enterPassword("testing@123");
//		KSU.msgOut("Gmail is now Open");
//		
//		//Click Email Link
//		WebActionUtil.clickEmail("Coinsquare - Confirm new email address");
//		
//		//Click Link in Email
//		//WebActionUtil.confirmLink();
//		WebElement confirmTab = WebDriverUtil.isElementPresent(theDriver, By.linkText("Confirm Email Address Change"), 30);
//		//confirmTab.click();
//		WebActionUtil.click(confirmTab, theDriver);
//		System.out.println("Confirmed Email Change");
//		
//		KSU.msgOut("Confirmed new Email Address");
//		
//		System.out.println("Start to get second tab:");
//		ArrayList<String> tabs = new ArrayList<>(theDriver.getWindowHandles());
//		theDriver.switchTo().window(tabs.get(2));
//		theDriver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
//		
//		
//		
//		/*StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
//        decryptor.setPassword(mpCryptoPassword);
//        passwordStr = ksLoadInfo.getPassword();
//		dePasswordStr = decryptor.decrypt(passwordStr);
//		WebElement password = WebDriverUtil.isElementPresent(theDriver, By.name("password"), 30);
//		WebActionUtil.clickToSendKeys(password, theDriver, dePasswordStr);
//		*/
//		
//		
//	}
//}
