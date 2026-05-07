package com.zsgs.bankManagement.features.customer.Transaction.AllHistory;

import java.util.List;

import com.zsgs.bankManagement.data.dto.DepositORWithdraw;

public class AllHistoryView {

	private AllHistoryModel allHistoryModel;

	public AllHistoryView() {
		this.allHistoryModel = new AllHistoryModel(this);
	}

	public void init() {
		allTransactionHistory();
	}

	private void allTransactionHistory() {

		List<DepositORWithdraw> customerTransactionHistory = allHistoryModel.getAllTransactionHistory();

		if (customerTransactionHistory.isEmpty()) {
			System.out.println("\nNo one is done Transactions yet\n");
			return;
		}

		System.out.println("\n<<<-----------All Transaction History--------->>>\n");
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