package com.zsgs.bankManagement.data.dto;

public class Manager {
	
	private String name;
	private String password;
	private int dob;
	private String emailID;
	private String mobileNo;
	
	public Manager() {}
		
	public Manager(String name, String password, int dob, String emailID, String mobileNo) {
	
		this.name = name;
		this.password = password;
		this.dob = dob;
		this.emailID = emailID;
		this.mobileNo = mobileNo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDob() {
		return dob;
	}
	public void setDob(int dob) {
		this.dob = dob;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	

}
