package com.zsgs.bankManagement.features.customerlogin;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dao.IAccountDAO;
import com.zsgs.bankManagement.data.repository.BankDB;

class CustomerLoginModel {

	CustomerLoginView customerLoginView;
	IAccountDAO iAccountDAOI;

	CustomerLoginModel(CustomerLoginView customerLoginView) {
		this.customerLoginView = customerLoginView;
		this.iAccountDAOI=new AccountDAOImpl();
	}

	boolean validateAccounrNumver(String accountNumber) {
//		return BankDB.getInstanse().getAccount(Long.parseLong(accountNumber)) != null;
		return iAccountDAOI.getAccountsRegistrationInfo(Long.parseLong(accountNumber))!=null;
	}
	
	boolean isAccountAcctive(String accountNumber) {
//		return BankDB.getInstanse().getAccount(Long.parseLong(accountNumber)).isActive();
		return iAccountDAOI.getAccountsRegistrationInfo(Long.parseLong(accountNumber)).isActive();
	}

	boolean isOnlyDigits(String accountNumber) {

		for (int i = 0; i < accountNumber.length(); i++) {
			if (!Character.isDigit(accountNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
