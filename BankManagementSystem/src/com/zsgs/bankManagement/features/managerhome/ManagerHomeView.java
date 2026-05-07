package com.zsgs.bankManagement.features.managerhome;

import java.util.Scanner;

import com.zsgs.bankManagement.features.accountcreation.AccountCreationView;
import com.zsgs.bankManagement.features.activation.ActivationORDeactivationView;
import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.customer.Transaction.AllHistory.AllHistoryView;
import com.zsgs.bankManagement.features.customer.Transaction.OneCustomerHistory.OneCustomerHistoryView;
import com.zsgs.bankManagement.features.customer.detail.DetailView;
import com.zsgs.bankManagement.features.customer.list.ListView;
import com.zsgs.bankManagement.features.signin.SigninView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class ManagerHomeView extends BaseView{

	private ManagerHomeModel managerHomeModel;
	private Scanner scanner;
	private SigninView signinView;

	public ManagerHomeView(SigninView signinView) {
		this.managerHomeModel = new ManagerHomeModel(this);
		this.scanner = ConsoleInput.getScanner();
		this.signinView=signinView;
	}

	public void init() {
		System.out.println("\n<<<---Welcome to Manager Menu The Manager of " + managerHomeModel.getDbInstanse().getName()+"--->>>");
		managerMenu();
	}

	private void managerMenu() {
		
	 outer:	while (true) {
			System.out.println("1.Account Creation\n2.Customer Details View\n3.One Customer Details\n4.Transaction History\n5.Transaction History One Customer\n6.Account Activation(OR)Deactivation\n7.Go Back Login menu\n8.ExcitApp");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				new AccountCreationView(this).init();
				break;
			case "2":
				new DetailView(this).init();
				break;
			case "3":
				new ListView().init(getAccountNumber());
				break;
			case "4":
				new AllHistoryView().init();
				break;
			case "5":
				new OneCustomerHistoryView().init(getAccountNumber());
				break;
			case "6":
				new ActivationORDeactivationView().init();
				break;
			case"7":
				 signinView.loginMenu();
				 break;
			case "8":
				exitApp();
				break;
			default:
				System.out.println("\nInvalid option. Please try again.\nRee Eneter Correct Option!....\n");
				break;
			}
		}
	}

	private String getAccountNumber() {
		showMessage("Enter The Customer Account Number : ");
		String accountNumber = "";
		do {
			accountNumber = scanner.nextLine();
			if (managerHomeModel.isOnlyDigits(accountNumber))
				break;
			errorMassage(
					"Invalid Input! ✖️ Please enter only numbers for the account number. Characters or symbols are not allowed.\nRee Enter The Customer Account Number :");
		} while (true);

		return accountNumber;
	}
}
