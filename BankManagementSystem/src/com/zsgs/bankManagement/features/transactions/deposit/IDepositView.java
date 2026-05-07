package com.zsgs.bankManagement.features.transactions.deposit;

public interface IDepositView {

	void init(String accountNumber) ;

	void depositAmont();

	void onSuccessFailed(String message);

	void showErrorMessage(String message);

}
