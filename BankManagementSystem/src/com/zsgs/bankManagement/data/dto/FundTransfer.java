package com.zsgs.bankManagement.data.dto;

import java.util.Date;

public class FundTransfer {
	
	private String transactionId; //TXN12345
	private Account senderAccount;
	private Account receiverAccount;
	private double transferAmount;
	private Date timestamp;
	private String status;
	
	public FundTransfer(String transactionId, Account senderAccount, Account receiverAccount, double transferAmount,
			Date timestamp, String status) {
		super();
		this.transactionId = transactionId;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transferAmount = transferAmount;
		this.timestamp = timestamp;
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
