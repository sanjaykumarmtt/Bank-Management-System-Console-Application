package com.zsgs.bankManagement.features.transactions.withdraw;


public interface IWithdrawModel {
	
	void init(String accountNumber);

	String withrawMone(double WithrawAmount);
	
	boolean isOnlyDigits(String WithrawAmount);
}
