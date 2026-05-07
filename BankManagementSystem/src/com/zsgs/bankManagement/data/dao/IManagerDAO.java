package com.zsgs.bankManagement.data.dao;

import java.util.List;

import com.zsgs.bankManagement.data.dto.Manager;

public interface IManagerDAO {

	/**
	 * Creates a new manager account.
	 *
	 * @param manager the manager object containing details to be created
	 */
	void createAccount(Manager manager);

	/**
	 * Retrieves the manager registration information.
	 *
	 * @return the manager information
	 */
	Manager getManagerRegistrationInfo();
	
	/**
	 * Retrieves a list of all managers.
	 *
	 * @return a list of managers
	 */
	List<Manager> getAllAccounts();

	/**
	 * Updates the account balance for the manager.
	 */
	void updateAccountBalance();
}
