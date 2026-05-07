package com.zsgs.bankManagement.features.accountcreation;

import java.util.Scanner;

import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.managerhome.ManagerHomeView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class AccountCreationView extends BaseView{

	private AccountCreationModel accountCreationModel;
	private Scanner scanner;
	private ManagerHomeView managerHomeView;

	public AccountCreationView(ManagerHomeView managerHomeView) {

		this.accountCreationModel = new AccountCreationModel(this);
		this.managerHomeView=managerHomeView;
		this.scanner = ConsoleInput.getScanner();
	}

	public void init() {
		System.out.println("<<---Creat New Customer Account-->>\n");
		enterUserAccountData();
	}

	private void enterUserAccountData() {
		Account account = new Account();
		account.setAccountNumber(accountCreationModel.generateAccountNumber());
		account.setAccountType(getAccountType());
		account.setActive(isAccountActivationRODeactivation());
		account.setUserName(getCustomerName());
		account.setAddress(getAddresType());
		account.setPhoneNumber(getCustomerMobileNo());
		accountCreationModel.createManager(account);
	
	}

	private String getAddresType() {
		String address="";
		System.out.println("Enter Customer Address:");
		while(true) {
			 address = scanner.nextLine();
			if(address!=null) {
				return address;
			}
			System.out.println("Ree Enter Customer Address:");
		}	
	}

	private String getAccountType() {
		String reault="";
		System.out.println("\nSelect account type\n1.Savings\n2.Current");
		while(true) {
			String option = scanner.nextLine();
			reault=accountCreationModel.getAccountType(option);
			if(reault!=null) {
				return reault;
			}
		}
	}
	private boolean isAccountActivationRODeactivation() {
		Object reault="";
		System.out.println("Select available or not\n1.Activation\n2.Deactivation");
		while(true) {
			String option = scanner.nextLine();
			reault=accountCreationModel.isAccountActivationRODeactivation(option);
			if(reault!=null) {
				return (boolean)reault;
			}
		}
	}
	
	private String getCustomerName() {
		String CustomerName = "";
		System.out.println("Enter Customer Name:");
		do {
			CustomerName = scanner.nextLine();
			String error = accountCreationModel.validateName(CustomerName);
			if (error != null) {
				errorMassage(error);
			} else {
				break;
			}
		} while (true);
		return CustomerName;
	}
	
	private String getCustomerMobileNo() {
		String customerMobile = "";
		System.out.println("Enter Customer Mobile number:");
		do {
			customerMobile = scanner.nextLine();
			String error = accountCreationModel.validateMobileNumber(customerMobile);
			if (error != null)
				errorMassage(error);
			else
				break;
		} while (true);
		return customerMobile;
	}
	void onCustomerAccountCreated(String message) {
		System.out.print(message);
		if(!scanner.nextLine().equalsIgnoreCase("Y")) managerHomeView.init();
		else
			enterUserAccountData();
	}

}