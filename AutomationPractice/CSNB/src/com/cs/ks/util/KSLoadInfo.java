package com.cs.ks.util;

public final class KSLoadInfo {
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */

	private final String homeUrl;
	private final String loginUrl;
	private final String username;
	private final String password;
	private final String emailHost;
	private final String emailSender;
	private final String emailReceiver;
	private final String emailPassword;
	private final String webOfferingBaseFilesLocation;
	private final String webOfferingTestFilesLocation;
	
	public KSLoadInfo(String homeUrl, 
			String loginUrl, 
			String username, 
			String password, 
			String emailHost, 
			String emailSender, 
			String emailReceiver, 
			String emailPassword, 
			String webOfferingBaseFilesLocation, 
			String webOfferingTestFilesLocation) {
		this.homeUrl = homeUrl;
		this.loginUrl = loginUrl;
        this.username = username;
        this.password = password;
		this.emailHost = emailHost;
		this.emailSender = emailSender;
        this.emailReceiver = emailReceiver;
        this.emailPassword = emailPassword;
        this.webOfferingBaseFilesLocation = webOfferingBaseFilesLocation;
        this.webOfferingTestFilesLocation = webOfferingTestFilesLocation;
	}

    public String getHomeURL() {
        return homeUrl;
    }
    
    public String getLoginURL() {
        return loginUrl;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getEmailHost() {
        return emailHost;
    }
    
    public String getEmailSender() {
        return emailSender;
    }

    public String getEmailReceiver() {
        return emailReceiver;
    }
    
    public String getEmailPassword() {
        return emailPassword;
    }
    
    public String getWebOfferingBaseFilesLocation() {
        return webOfferingBaseFilesLocation;
    }
    
    public String getWebOfferingTestFilesLocation() {
        return webOfferingTestFilesLocation;
    }
}