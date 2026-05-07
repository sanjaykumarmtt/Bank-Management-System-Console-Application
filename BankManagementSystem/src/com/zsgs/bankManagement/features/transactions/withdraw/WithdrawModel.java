package com.zsgs.bankManagement.features.transactions.withdraw;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.repository.BankDB;
import com.zsgs.bankManagement.features.transactions.BaseTransactionModel;

class WithdrawModel extends BaseTransactionModel implements IWithdrawModel{

	private IWithdrawMadeltoPresenter iwithdrawMadeltoPresenter;
	private Account account;
	
	WithdrawModel(IWithdrawMadeltoPresenter iwithdrawMadeltoPresenter) {
		this.iwithdrawMadeltoPresenter = iwithdrawMadeltoPresenter;
	}

	@Override
	public void init(String accountNumber) {
		
//		account=BankDB.getInstanse().getAccount(Long.parseLong(accountNumber));
		account=new AccountDAOImpl().getAccountsRegistrationInfo(Long.parseLong(accountNumber));
		if(account==null) {
			iwithdrawMadeltoPresenter.showErrorMessage("\n<<<-------The data in this Account number : "+accountNumber+" has not yet been saved in DB------->>>\n");
			return;
		}else if(!isAccountAcctive(account)) {
			iwithdrawMadeltoPresenter.showErrorMessage("\n<<<-------The manager has temporarily suspended : "+account.getUserName()+"------->>>\n");
			return;
		}	
		iwithdrawMadeltoPresenter.withdrawAmont();
	}

	@Override
	public String withrawMone(double withrawAmount) {
		
		if(!isSufficientBalance(withrawAmount,account)) return "Can't Withraw this money : "+withrawAmount+"!\nBecause You don't have enough money in your bank!......\n"; 
	
		if(!isWithdrawBalance(withrawAmount,account)) return "Can't Withraw this money : "+withrawAmount+" Please enter valid amount\n";
			
			historyTransaction(account,"Withraw",-withrawAmount);
		return "<<<----"+withrawAmount+" RS.Is successfully Withraw----->>>\n";
		
	}
	
	public boolean isOnlyDigit(String accountNumber) {
		return isOnlyDigits(accountNumber);
	}
	

}
