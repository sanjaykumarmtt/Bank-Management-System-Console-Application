package com.zsgs.bankManagement.features.transactions.fundtransfer;

interface IFundTransferView {

	void init(String accountNumber);

	void validateAmountANDREAccountNumber();

	void Message(String message);

//	void MessageTwo(String message);
	
	void onSuccessFailed(String message);

	void showErrorMessage(String message);
	
}

