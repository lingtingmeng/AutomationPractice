package com.cs.ui.test.nb.dashboard;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.cs.ks.util.KSU;
import com.cs.ui.feature.Dashboard;
import com.cs.ui.feature.Login;
import com.cs.ui.feature.Logout;
import com.cs.ui.util.WebDriverBase;


public class C5479_loginLogout extends WebDriverBase{

	
	@Test
	public void loginLogout() throws Exception
	{		
		
		KSU.testMsgOut("Test Case C5479: Login from home page & Logout");
		
		//login	from home page
		Login.loginFromHome();
		theDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Dashboard.dashboardTab();
		
		//logout
		Logout.logout();
		KSU.testMsgOut("Test Case C5479 (Done): Login from home page & Logout");
		Thread.sleep(3000);
	}
	
}
