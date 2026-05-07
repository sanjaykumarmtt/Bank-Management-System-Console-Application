package com.zsgs.bankManagement.features.transactions.withdraw;

public interface IWithdrawPresenter {
	
	void init(String accountNumber);
	
	boolean isOnlyDigits(String WithrawAmount);
	
	String withdraw(double WithrawAmount);
}
