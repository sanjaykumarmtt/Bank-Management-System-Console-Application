package com.zsgs.bankManagement.data.dao;

import java.util.List;

import com.zsgs.bankManagement.data.dto.DepositORWithdraw;

public interface IDepositORWithdrawDAO {
	
	/**
	 * Records a deposit, withdrawal, or fund transfer transaction.
	 *
	 * @param depositORWithdraw the transaction details object
	 */
	void setDepositORWithdrawORFundTransfer(DepositORWithdraw  depositORWithdraw);

	/**
	 * Retrieves the transaction history for a specific account.
	 *
	 * @param accountNumber the account number to fetch transactions for
	 * @return a list of transactions associated with the account
	 */
	List<DepositORWithdraw>  getOneDepositORWithdraw(long accountNumber);
	
	/**
	 * Retrieves all transactions recorded in the system.
	 *
	 * @return a list of all transactions
	 */
	List<DepositORWithdraw> getAllDepositORWithdraw();

}
