package com.zsgs.bankManagement.features.transactions;

import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.dto.DepositORWithdraw;

public interface TransactionService {

	boolean isDepositBalance(double depositAmount,Account account);
	
	boolean isWithdrawBalance(double amount,Account account);
	
	boolean isValidateAccount(Account account);
	
	boolean isAccountAcctive(Account account);
	
	default String generateTransactionId() {
		return "TXN" + System.currentTimeMillis();
	}
	
	boolean isSufficientBalance(double customerAskMoney,Account account);
  
	void historyTransaction(Account account,String transactType,double depositAmount);

}
