package com.zsgs.bankManagement.features.managerhome;

import com.zsgs.bankManagement.data.dao.ManagerDAOImpl;
import com.zsgs.bankManagement.data.dto.Manager;
import com.zsgs.bankManagement.data.repository.BankDB;

class ManagerHomeModel {
	
	ManagerHomeView managerHomeView;
	Manager manager;
//	BankDB manager;

	public ManagerHomeModel(ManagerHomeView managerHomeView) {
		this.managerHomeView = managerHomeView;
//		this.manager=BankDB.getInstanse();
		this.manager=new ManagerDAOImpl().getManagerRegistrationInfo();
	}

//	Manager getDbInstanse() {
//		return manager.getManagerRegistrationInfo();
//	}
	
	Manager getDbInstanse() {
		return manager;
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
