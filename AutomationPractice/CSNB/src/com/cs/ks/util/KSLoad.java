package com.cs.ks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.cs.ui.util.WebOsUtil;


public class KSLoad {

	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	public static KSLoadInfo getLoadInfo() {
	    String homeUrl,
	    loginUrl, 
	    username, 
	    password, 
	    emailHost, 
	    emailSender, 
	    emailReceiver, 
	    emailPassword, 
	    webOfferingBaseFilesLocation, 
	    webOfferingTestFilesLocation;
		
		File file = null;
		
		if (WebOsUtil.isMac() == true || WebOsUtil.isLinux() == true) {     
	    		file = new File("./files/properties/mac-linux-users.properties");
		} else if (WebOsUtil.isWindows() == true) {
			file = new File(".\\files\\properties\\win-users.properties");
		}
		
		FileInputStream fileInput = null;
		
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		homeUrl = prop.getProperty("homeURL");
		loginUrl = prop.getProperty("loginURL");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		
		emailHost = prop.getProperty("emailHost");
		emailSender = prop.getProperty("emailSender");
		emailReceiver = prop.getProperty("emailReceiver");
		emailPassword = prop.getProperty("emailPassword");
		webOfferingBaseFilesLocation = prop.getProperty("webOfferingBaseFilesLocation");
		webOfferingTestFilesLocation = prop.getProperty("webOfferingTestFilesLocation");
		
	    return new KSLoadInfo(homeUrl, 
	    		loginUrl, 
	    		username, 
	    		password, 
	    		emailHost, 
	    		emailSender, 
	    		emailReceiver, 
	    		emailPassword, 
	    		webOfferingBaseFilesLocation, 
	    		webOfferingTestFilesLocation);
		
	}
	
}