package com.zsgs.bankManagement.features.transactions.fundtransfer;

interface IFundTransferPresentertoModel {

	void validateAmountANDREAccountNumber();

	void onSuccessFailed(String message);

	void showErrorMessage(String message);
	
}
