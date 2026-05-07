package com.zsgs.bankManagement.features.customer.list;

import java.util.Scanner;

import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.managerhome.ManagerHomeView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class ListView extends BaseView {

	private ListModel listModel;
	private Object managerHomeView;
	private Scanner scanner;

	public ListView() {
		this.listModel = new ListModel(this);
	
		this.scanner = ConsoleInput.getScanner();
	}

	public void init(String accountNumber) {
		showOneData(accountNumber);
	}

	private void showOneData(String accountNumber) {
		Account oneAccount = listModel.getCustomerInfo(Long.parseLong(accountNumber));

		if (oneAccount != null) {
			System.out.println("\n<<<----This is " + oneAccount.getUserName() + " Data----->>");
			System.out.printf("%-5s %-20s %-15s %-15s %-10s %-15s %-15s %-12s %n", "ID", "AccountNumber", "balance",
					"AccountType", "isActive", "CustomerName", "Address", "PhoneNumber");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-5d %-20d %-15.2f %-16s %-10b %-15s %-15s %-12s %n", oneAccount.getUserId(),
					oneAccount.getAccountNumber(), oneAccount.getBalance(), oneAccount.getAccountType(),
					oneAccount.isActive(), oneAccount.getUserName(), oneAccount.getAddress(),
					oneAccount.getPhoneNumber());

			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------\n\n");
			System.out.println("<---Bank Branch Detail--->");
			System.out.printf("%-19s %-20s %-15s %-48s %-10s %n", "ifscCode", "MicrCode", "BranchName", "Address",
					"City");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-19s %-20s %-15s %-48s %-10s %n%n", oneAccount.getBranch().getIfscCode(),
					oneAccount.getBranch().getMicrCode(), oneAccount.getBranch().getBranchName(),
					oneAccount.getBranch().getAddress(), oneAccount.getBranch().getCity());

		} else {
			showMessage("\n<<---Account not found. Please check your account number and try again.✖️✖️✖️✖️--->>\n");
		}
	
//		if (customerLoginModel.validateAccounrNumver(accountNumber)) {
//			showMessage("<<---Login Successful! Your account details have been verified.✔️✔️✔️✔️--->>\n");
//			customerMenu();
//		} else {
//			tryAgain(
//					\nDo you want to TryAgain? Type Y or N : ");
//		}
//		
		
	}

}
