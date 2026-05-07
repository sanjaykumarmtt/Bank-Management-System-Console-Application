package com.zsgs.bankManagement.features.transactions.fundtransfer;

interface IFundTransFerModel {

	void init(String accountNumber);

	String numberValidation(String amountValidation);

	String amountIsAvlabel(String amountValidation);
	
	String receiverAccountIsAvlabel(String amountValidation);

	void getAmounttoGive(String amount,String reAccountNumber);
}
