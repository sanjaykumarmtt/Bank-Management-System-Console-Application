package com.zsgs.bankManagement.data.dao;

import java.util.List;

import com.zsgs.bankManagement.data.dto.Account;

public interface IAccountDAO {

	/**
	 * Creates a new account in the system.
	 *
	 * @param manager the account object containing details to be created
	 */
	void createAccount(Account manager);

	/**
	 * Retrieves the registration information for a specific account.
	 *
	 * @param accountNumber the account number to fetch details for
	 * @return the account information
	 */
	Account getAccountsRegistrationInfo(long accountNumber);
	
	/**
	 * Retrieves a list of all accounts.
	 *
	 * @return a list of all accounts in the system
	 */
	List<Account> getAllAccounts();
	
	/**
	 * Updates the balance of a specific account.
	 *
	 * @param account the account object containing the updated balance
	 */
	public void updateBalance(Account account);
	
	/**
	 * Activates or deactivates an account.
	 *
	 * @param isCon         true to activate, false to deactivate
	 * @param accountNumber the account number to update
	 */
	void ActivationORDeactivation(boolean isCon,long accountNumber);
	
	
}
