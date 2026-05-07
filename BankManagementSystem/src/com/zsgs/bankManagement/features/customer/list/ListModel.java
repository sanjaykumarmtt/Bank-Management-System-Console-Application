package com.zsgs.bankManagement.features.customer.list;

import java.util.Collections;
import java.util.List;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.repository.BankDB;

class ListModel {
	
	ListView listView;
	

	public ListModel(ListView listView) {
		this.listView = listView;
	}
	
	public Account getCustomerInfo(long accountNumber) {
//		return BankDB.getInstanse().getAccount(accountNumber); 
		return new AccountDAOImpl().getAccountsRegistrationInfo(accountNumber);
	}
}
