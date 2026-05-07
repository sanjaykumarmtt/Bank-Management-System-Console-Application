package com.zsgs.bankManagement.features.customer.Transaction.OneCustomerHistory;

import java.util.List;

import com.zsgs.bankManagement.data.dto.DepositORWithdraw;

public class OneCustomerHistoryView {

	private OneCustomerHistoryModel oneCustomerHistoryModel;

	public OneCustomerHistoryView() {
		this.oneCustomerHistoryModel = new OneCustomerHistoryModel(this);
	}

	public void init(String accountNumber) {
		allTransactionHistory(accountNumber);
	}

	private void allTransactionHistory(String accountNumber) {
		
		

		List<DepositORWithdraw> customerTransactionHistory = oneCustomerHistoryModel
				.getAllTransactionHistory(Long.parseLong(accountNumber));
		
		if(customerTransactionHistory.isEmpty()) {
			System.out.println("\nNo transactions have been made in this account.\n");
			return;
		}

		System.out.println("\n<<<-----------One Customer Transaction History--------->>>\n");
		int i = 1;

		System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-30s %-29s %-20s %-20s %n", "So.n",
				"TransactionId", "AccountNo", "Currentbalance", "Name", "TransacAmount", "Account Type",
				"Transact Type", "Receiver Name", "Receiver Account Number", "Transact Time", "Transact Date");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (DepositORWithdraw getCustomerTransactionHistory : customerTransactionHistory) {

			getCustomerTransactionHistory.setReceiverName(getCustomerTransactionHistory.getReceiverName() != null
					? getCustomerTransactionHistory.getReceiverName()
					: "It is not fun transfer");

			System.out.printf("%-5s %-20s %-20d %-20.2f %-20s  %-20.2f %-20s %-20s %-30s %-29d %-20s %-20s %n", i,
					getCustomerTransactionHistory.getTransactionId(), getCustomerTransactionHistory.getAccountNo(),
					getCustomerTransactionHistory.getCurrentbalance(), getCustomerTransactionHistory.getCustomerName(),
					getCustomerTransactionHistory.getTransacAmount(), getCustomerTransactionHistory.getType(),
					getCustomerTransactionHistory.getTransactType(), getCustomerTransactionHistory.getReceiverName(),
					getCustomerTransactionHistory.getTransacAccountNumber(),
					getCustomerTransactionHistory.getCurrentTime(), getCustomerTransactionHistory.getCurrentDate());
			i++;
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

}
