package com.zsgs.bankManagement.features.signin;

import java.util.Scanner;

import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.customerlogin.CustomerLoginView;
import com.zsgs.bankManagement.features.managerhome.ManagerHomeView;
import com.zsgs.bankManagement.features.signup.SigUpView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class SigninView extends BaseView {

	private SigninModel signinModel;
	private Scanner scanner;

	public SigninView() {
		this.signinModel = new SigninModel(this);
		this.scanner = ConsoleInput.getScanner();
	}

	public void init() {
		
		System.out.println(
				"\n<<----Welcome to SBI Bank Login Page----->>\n");
		loginMenu();
	}

	public void loginMenu() {
		while (true) {
			System.out.println("<<--Choosde Your Rule-->>\n1.ManagerSigUp\n2.ManagerLogin\n3.CustomerLogin\n4.ExitApp");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				new SigUpView().init();
				break;
			case "2":
				managerLogin();
				break;
			case "3":
				new CustomerLoginView(this).init();
				break;
			case "4":
				exitApp();
				break;
			default:
				System.out.println("\nInvalid option. Please try again.\nRee Eneter Correct Option\n");
			}
		}
	}

	private void managerLogin() {
		
		if (signinModel.getDbInstanse() == null) {
			System.out.println("\nplease go to SignUp After then login!.........\n");
			new SigUpView().init();
		}
		
		String loginMail = "";
		String password = "";
		System.out.println("\nEnter The Manager Login Email : ");
		do {
			loginMail = scanner.nextLine();
			String error = signinModel.validateEmailType(loginMail);
			if (error != null) {
				showErrorMessage(error);
				continue;
			}
			error = signinModel.checkManagerEmail(loginMail);
			if (error != null)
				onSignInFailed(error);
			else
				break;
		} while (true);

		System.out.println("Eneter The Manager Login Password:");
		do {
			password = scanner.nextLine();
			String error = signinModel.validatePassword(password);
			if (error != null)
				onInvalidCredentials(error);
			else
				break;
		} while (true);
		// onSignInSuccessful();
		new ManagerHomeView(this).init();
		return;
	}

	void onSignInSuccessful() {
		new ManagerHomeView(this).init();
	}

	private void onInvalidCredentials(String massage) {
		System.out.print(massage);
		if (!scanner.nextLine().equalsIgnoreCase("Y"))
			loginMenu();
		System.out.println("Ree Enter Password:");
	}

	void onSignInFailed(String message) {
		System.out.println(message);
	}

	void showErrorMessage(String message) {
		System.out.println(message);
	}
}
