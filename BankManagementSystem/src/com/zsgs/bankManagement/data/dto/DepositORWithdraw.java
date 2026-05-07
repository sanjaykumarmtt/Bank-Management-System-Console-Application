package com.zsgs.bankManagement.data.dto;

public class DepositORWithdraw {

	private String transactionId;
	private long accountNo;
	private String customerName;
	private double currentbalance;
	private double transacAmount;
	private String type;    	    	//Account type
	private String transactType;	   //-(Deposit / Withdraw).
	private String receiverName;
	private long transacAccountNumber;
	private String currentTime;
	private String currentDate;
	
	public DepositORWithdraw() {}

	public DepositORWithdraw(String transactionId, long accountNo, String customerName, double currentbalance,
			double transacAmount, String type, String transactType, String receiverName, long transacAccountNumber,
			String currentTime, String currentDate) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.currentbalance = currentbalance;
		this.transacAmount = transacAmount;
		this.type = type;
		this.transactType = transactType;
		this.receiverName = receiverName;
		this.transacAccountNumber = transacAccountNumber;
		this.currentTime = currentTime;
		this.currentDate = currentDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(double currentbalance) {
		this.currentbalance = currentbalance;
	}

	public double getTransacAmount() {
		return transacAmount;
	}

	public void setTransacAmount(double transacAmount) {
		this.transacAmount = transacAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransactType() {
		return transactType;
	}

	public void setTransactType(String transactType) {
		this.transactType = transactType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public long getTransacAccountNumber() {
		return transacAccountNumber;
	}

	public void setTransacAccountNumber(long transacAccountNumber) {
		this.transacAccountNumber = transacAccountNumber;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
}