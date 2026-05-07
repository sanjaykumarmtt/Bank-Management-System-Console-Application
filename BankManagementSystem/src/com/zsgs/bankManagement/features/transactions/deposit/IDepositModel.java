package com.zsgs.bankManagement.features.transactions.deposit;


public interface IDepositModel {
	
	void init(String accountNumber);

	String depositBalance(double depositAmount);
	
	boolean isOnlyDigits(String depositAmount);
}
