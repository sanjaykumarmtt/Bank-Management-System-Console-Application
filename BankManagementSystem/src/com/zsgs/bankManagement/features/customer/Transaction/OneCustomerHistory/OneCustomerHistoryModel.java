package com.zsgs.bankManagement.features.customer.Transaction.OneCustomerHistory;

import java.util.List;

import com.zsgs.bankManagement.data.dao.DepositORWithdrawDAO;
import com.zsgs.bankManagement.data.dao.IDepositORWithdrawDAO;
import com.zsgs.bankManagement.data.dto.DepositORWithdraw;


class OneCustomerHistoryModel {
	
	//private OneCustomerHistoryView oneCustomerHistoryView;
	private IDepositORWithdrawDAO iDepositORWithdrawDAO;

	public OneCustomerHistoryModel(OneCustomerHistoryView oneCustomerHistoryView) {
		
//		this.oneCustomerHistoryView = oneCustomerHistoryView;
		this.iDepositORWithdrawDAO=new DepositORWithdrawDAO();
	}

	public List<DepositORWithdraw> getAllTransactionHistory(long accountNumber) {
		// return BankDB.getInstanse().getDepositORWithdrawInfo(accountNumber);
		return iDepositORWithdrawDAO.getOneDepositORWithdraw(accountNumber);
	}
	
	

}
