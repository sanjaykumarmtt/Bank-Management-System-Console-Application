package com.zsgs.bankManagement.features.transactions.withdraw;

public interface IWithdrawView {

	void init(String accountNumber) ;

	void withdrawAmont();

	void onSuccessFailed(String message);

	void showErrorMessage(String message);

}
