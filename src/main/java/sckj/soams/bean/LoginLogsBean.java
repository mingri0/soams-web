package sckj.soams.bean;

import sckj.soams.entity.LoginLogs;

public class LoginLogsBean extends LoginLogs{
	
	private String username;
	
	private String loginname;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
}
