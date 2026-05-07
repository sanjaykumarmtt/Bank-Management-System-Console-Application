package com.zsgs.bankManagement.features.customer.Transaction.AllHistory;

import java.util.List;

import com.zsgs.bankManagement.data.dao.DepositORWithdrawDAO;
import com.zsgs.bankManagement.data.dao.IDepositORWithdrawDAO;
import com.zsgs.bankManagement.data.dto.DepositORWithdraw;
import com.zsgs.bankManagement.data.repository.BankDB;

class AllHistoryModel {

	private AllHistoryView allHistoryView;
	private IDepositORWithdrawDAO iDepositORWithdrawDAO;

	AllHistoryModel(AllHistoryView allHistoryView) {
		this.allHistoryView = allHistoryView;
		this.iDepositORWithdrawDAO=new DepositORWithdrawDAO();
	}
	
	List<DepositORWithdraw> getAllTransactionHistory() {
		//return BankDB.getInstanse().getDepositORWithdrawInfo();
		return iDepositORWithdrawDAO.getAllDepositORWithdraw();
	}

}
