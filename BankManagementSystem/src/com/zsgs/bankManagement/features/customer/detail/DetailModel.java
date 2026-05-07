package com.zsgs.bankManagement.features.customer.detail;

import java.util.List;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dao.IAccountDAO;
import com.zsgs.bankManagement.data.dto.Account;

class DetailModel {
	
	DetailView detailView;
//	BankDB bankDB;
	
	private IAccountDAO iAccountDAO;

	public DetailModel(DetailView detailView) {
		this.detailView = detailView;
//		this.bankDB=BankDB.getInstanse();
		this.iAccountDAO=new AccountDAOImpl();
	}
	
	List<Account> getCustomerData() {
//		return bankDB.getCustomerInfo();
		return iAccountDAO.getAllAccounts();
	}
	

}
