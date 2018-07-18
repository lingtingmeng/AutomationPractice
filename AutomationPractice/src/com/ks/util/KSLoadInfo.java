package com.ks.util;

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
	
	public KSLoadInfo(String homeUrl, String loginUrl, String username, String password) {
		this.homeUrl = homeUrl;
		this.loginUrl = loginUrl;
        this.username = username;
        this.password = password;
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
}