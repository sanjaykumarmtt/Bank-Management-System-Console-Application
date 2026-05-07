package com.zsgs.bankManagement.features.activation;

import java.util.Scanner;


import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.managerhome.ManagerHomeView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class ActivationORDeactivationView extends BaseView {

	private ActivationORDeactivationModel balancEinquiryModel;
	private Scanner scanner;

	public ActivationORDeactivationView() {
		this.balancEinquiryModel = new ActivationORDeactivationModel(this);
		this.scanner = ConsoleInput.getScanner();
	}

	public void init() {
		activationORDeactivationModel();
	}

	private void activationORDeactivationModel() {
		
		System.out.println("<<-----Activation (OR) Deactivation Menu---->>\nEnter Account Number : ");
		String accountNumber="";
		do {
			accountNumber = scanner.nextLine();
			if(!balancEinquiryModel.isOnlyDigits(accountNumber)) errorMassage("Invalid Input! ✖️ Please enter only numbers for the account number. Characters or symbols are not allowed.\n"
					+ "Ree Enter The Customer Account Number : ");
			else
				break;
		}while(true);
		
		
		System.out.println("1.Activation\n2.Deactivation");
		String option = scanner.nextLine();
		
		switch (option) {
		case "1":
			balancEinquiryModel.activation(Long.parseLong(accountNumber));
			break;
		case "2":
			balancEinquiryModel.deactivation(Long.parseLong(accountNumber));
			break;
		default:
			System.out.println("\n---***Invalid option. Please try again***---.\n");
		}
	}
}