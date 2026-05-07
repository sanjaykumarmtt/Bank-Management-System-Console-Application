package com.zsgs.bankManagement.features.transactions.deposit;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.repository.BankDB;
import com.zsgs.bankManagement.features.transactions.BaseTransactionModel;

class DepositModel extends BaseTransactionModel implements IDepositModel {

	private IDepositMadeltoPresenter iDepositMadeltoPresenter;
	private Account account = null;

	DepositModel(IDepositMadeltoPresenter iDepositMadeltoPresenter) {
		this.iDepositMadeltoPresenter = iDepositMadeltoPresenter;
		}

	public void init(String accountNumber) {
		//account=BankDB.getInstanse().getAccount(Long.parseLong(accountNumber));
		account=new AccountDAOImpl().getAccountsRegistrationInfo(Long.parseLong(accountNumber));
		
		if(account==null) {
			iDepositMadeltoPresenter.showErrorMessage("\n<<<-------The data in this Account number : "+accountNumber+" has not yet been saved in DB------->>>\n");
			return;
		}else if(!isAccountAcctive(account)) {
			iDepositMadeltoPresenter.showErrorMessage("\n<<<-------The manager has temporarily suspended : "+account.getUserName()+"------->>>\n");
			return;
		}
		
		iDepositMadeltoPresenter.depositAmont();
	}

	public String depositBalance(double depositAmount) {
		
		if(isDepositBalance(depositAmount,account)) {
			historyTransaction(account,"Deposit",depositAmount);
			return "<<<----"+depositAmount+" RS.Is successfully deposited----->>>\n";
		}
		return "Can't deposit this money : "+depositAmount+"!......\n";
	}
	
	public boolean isOnlyDigit(String accountNumber) {
		return isOnlyDigits(accountNumber);
	}

}
