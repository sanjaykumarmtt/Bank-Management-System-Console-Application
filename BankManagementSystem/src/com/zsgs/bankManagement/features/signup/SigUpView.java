package com.zsgs.bankManagement.features.signup;

import java.util.Scanner;

import com.zsgs.bankManagement.data.dto.Manager;
import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.signin.SigninView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class SigUpView extends BaseView {

	private SigUpModel sigUpModel;
	private Scanner scanner;

	public SigUpView() {
		this.sigUpModel = new SigUpModel(this);
		this.scanner = ConsoleInput.getScanner();
	}

	public void init() {
		sigUpModel.inti();
	}

	void registerManager() {
		Manager manager = new Manager();
		manager.setName(getManagerName());
		manager.setEmailID(getManagerEmail());
		manager.setMobileNo(getManagerMobileNo());
		manager.setDob(getDob());
		manager.setPassword(getPassWord());
		sigUpModel.createManager(manager);
	}

	private String getManagerName() {
		String managerName = "";
		System.out.println("Enter Manager Name:");
		do {
			managerName = scanner.nextLine();
			String error = sigUpModel.validateName(managerName);
			if (error != null) {
				errorMassage(error);
			} else {
				break;
			}
		} while (true);
		return managerName;
	}

	private String getManagerEmail() {
		String managerEmail = "";
		System.out.println("Enter Manager Email:");
		do {
			managerEmail = scanner.nextLine();
			String error = sigUpModel.validateEmail(managerEmail);
			if (error != null)
				errorMassage(error);
			else
				break;
		} while (true);

		return managerEmail;
	}

	private String getManagerMobileNo() {
		String managerMobile = "";
		System.out.println("Enter Mobile number:");
		do {
			managerMobile = scanner.nextLine();
			String error = sigUpModel.validateMobileNumber(managerMobile);
			if (error != null)
				errorMassage(error);
			else
				break;
		} while (true);
		return managerMobile;
	}

	private int getDob() {
		while (true) {
			System.out.print("Enter your date of birth (dd-MM-yyyy): ");
			String input = scanner.nextLine();
			Integer dob = sigUpModel.parseDateOfBirth(input);
			if (dob != null)
				return dob;
			errorMassage("Enter a valid date. You must be at least 18 years old.");
		}
	}

	private String getPassWord() {
		String managerPassWord = "";
		System.out.println("Enter PassWord:");
		do {
			managerPassWord = scanner.nextLine();
			String error = sigUpModel.validatePassWord(managerPassWord);
			if (error != null)
				errorMassage(error);
			else
				break;
		} while (true);
		System.out.println("Enter Confirm PassWord:");
		do {
			String error = sigUpModel.validateConfirmPassword(managerPassWord, scanner.nextLine());
			if (error != null)
				errorMassage(error);
			else
				break;
		} while (true);

		return managerPassWord;
	}
	
	void onRegistrationSuccess(String massage) {
		System.out.print(massage);
	    if (!scanner.nextLine().equalsIgnoreCase("Y")) exitApp();
		new SigninView().init();
	}
}

