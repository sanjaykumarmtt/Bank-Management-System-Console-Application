package com.zsgs.bankManagement.features.transactions.fundtransfer;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.repository.BankDB;
import com.zsgs.bankManagement.features.transactions.BaseTransactionModel;

class FundTransferModel extends BaseTransactionModel implements IFundTransFerModel {

	private IFundTransferPresentertoModel iFundTransferPresentertoModel;
	private Account account=null;
	private Account receiverAccount=null;

	public FundTransferModel(IFundTransferPresentertoModel iFundTransferPresentertoModel) {

		this.iFundTransferPresentertoModel = iFundTransferPresentertoModel;
	}

	@Override
	public void init(String accountNumber) {

//		account = BankDB.getInstanse().getAccount(Long.parseLong(accountNumber));
		account=new AccountDAOImpl().getAccountsRegistrationInfo(Long.parseLong(accountNumber));
		if (account == null) {
			iFundTransferPresentertoModel.showErrorMessage("\n<<<-------The data in this Account number : "
					+ accountNumber + " has not yet been saved in DB------->>>\n");
			return;
		} else if (!isAccountAcctive(account)) {
			iFundTransferPresentertoModel.showErrorMessage(
					"\n<<<-------The manager has temporarily suspended : " + account.getUserName() + "------->>>\n");
			return;
		}
		iFundTransferPresentertoModel.validateAmountANDREAccountNumber();

	}

	@Override
	public String numberValidation(String amountValidation) {

		if (isOnlyDigits(amountValidation)) return null;
		
		return "Please Ree enter valid number : ";

	}

	@Override
	public String amountIsAvlabel(String amountValidation) {

		if (!isSufficientBalance(Double.parseDouble(amountValidation), account))
			return "Can't TransFer this money : " + amountValidation
					+ "!\nBecause You don't have enough money in your bank!......\n\n"
					+ "Do you want to proceed or back? Type Y or N : ";

		return null;

	}
	
	@Override
	public String receiverAccountIsAvlabel(String accountNumber) {
		
//		receiverAccount=BankDB.getInstanse().getAccount(Long.parseLong(amountValidation));
		receiverAccount=new AccountDAOImpl().getAccountsRegistrationInfo(Long.parseLong(accountNumber));
		
		if(receiverAccount==null) return "There is no such account!✖️✖️✖️....\nDo you want to proceed or back? Type Y or N : ";
			
		if(!receiverAccount.isActive()) return "\n<<<-------The Manager has temporarily suspended : "+accountNumber+" ✖️✖️✖️------->>>\nDo you want to proceed or back? Type Y or N : ";
	
		return null;
	}


	@Override
	public void getAmounttoGive(String amount,String reAccountNumber) {
		
		isWithdrawBalance(Double.parseDouble(amount),account);
		isDepositBalance(Double.parseDouble(amount),receiverAccount);
		
		fundeTransactionHistory(account,"FundTransfer",-Double.parseDouble(amount),receiverAccount.getUserName(),receiverAccount.getAccountNumber());
	
		iFundTransferPresentertoModel.onSuccessFailed("<<<----Money was successfully sent to "+receiverAccount.getUserName()+"✔️✔️✔️.----->>>\n");;
		
	}


}
