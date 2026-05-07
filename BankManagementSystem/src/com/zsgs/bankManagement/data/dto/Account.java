package com.zsgs.bankManagement.data.dto;


public class Account extends User{
//	create table Account(id int auto_increment,accountNumber bigint primary key,balance decimal(5,3),accountType varcher(20),isActi)
	
	private long accountNumber=0;
	private double balance=500d;
	private String accountType; //(Savings / Current).
	private boolean isActive;
	
	private Branch branch;
	
	public Account() {
		this.branch = new Branch();
	}
	
	public Account(long accountNumber, double balance, String accountType, boolean isActive) {
		
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.isActive = isActive;
	
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Branch getBranch() {
		return branch;
	}

}
