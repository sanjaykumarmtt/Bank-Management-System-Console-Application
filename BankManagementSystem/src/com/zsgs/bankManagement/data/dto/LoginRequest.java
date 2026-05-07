package com.zsgs.bankManagement.data.dto;

public class LoginRequest {
	private String emailID;
	private String password;
	
	public LoginRequest(String emailID, String password) {
		this.emailID = emailID;
		this.password = password;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
