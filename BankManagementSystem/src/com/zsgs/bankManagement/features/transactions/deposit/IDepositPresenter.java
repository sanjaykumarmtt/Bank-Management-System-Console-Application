package com.zsgs.bankManagement.features.transactions.deposit;

public interface IDepositPresenter {
	
	void init(String accountNumber);
	
	boolean isOnlyDigits(String depositAmount);
	
	String depositBalance(double depositAmount);
}
