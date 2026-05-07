package com.zsgs.bankManagement.features.customerlogin;

import java.util.Scanner;

import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.customer.Transaction.OneCustomerHistory.OneCustomerHistoryView;
import com.zsgs.bankManagement.features.customer.list.ListView;
import com.zsgs.bankManagement.features.signin.SigninView;
import com.zsgs.bankManagement.features.transactions.deposit.DepositView;
import com.zsgs.bankManagement.features.transactions.fundtransfer.FundTransferView;
import com.zsgs.bankManagement.features.transactions.withdraw.WithdrawView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class CustomerLoginView extends BaseView {

	private CustomerLoginModel customerLoginModel;
	private Scanner scannaer;
	private SigninView signinView;

	public CustomerLoginView(SigninView signinView) {
		this.customerLoginModel = new CustomerLoginModel(this);
		this.scannaer = ConsoleInput.getScanner();
		this.signinView = signinView;
	}

	public void init() {
		showMessage("<<<----------Welcom to Customer Login---------->>>");
		longinCustomerAccount();
	}

	private void longinCustomerAccount() {
		showMessage("Enter The Customer Account Number : ");
		String accountNumber = "";
		do {
			accountNumber = scannaer.nextLine();
			if (customerLoginModel.isOnlyDigits(accountNumber))
				break;
			errorMassage(
					"Invalid Input! ✖️ Please enter only numbers for the account number. Characters or symbols are not allowed.\nRee Enter The Customer Account Number :");
		} while (true);

		if (customerLoginModel.validateAccounrNumver(accountNumber)) {
			if(customerLoginModel.isAccountAcctive(accountNumber)) {
				showMessage("\n<<---Login Successful! Your account details have been verified.✔️✔️✔️✔️--->>\n");
				customerMenu(accountNumber);
			}else {
				errorMassage("\n<<<-------The Manager has temporarily suspended : "+accountNumber+" ✖️✖️✖️------->>>\n");
			}
			
		} else {
			tryAgain(
					"<<---Login Failed! Account details not found. Please check your account number and try again.✖️✖️✖️--->>\nDo you want to TryAgain? Type Y or N : ");
		}
	}
	public void customerMenu(String accountNumber) {
		
	outer:	while(true) {
		
		showMessage("\n <<<-----Customer Menu---->>>\n1.View Account Detail\n2.Deposit\n3.Withdraw\n4.Transaction History\n5.FundtransFer\n6.Go Back Login menu\n7.ExiteApp\n");
			String option = scannaer.nextLine();
			switch (option) {
			case "1":
				new ListView().init(accountNumber);
				break;
			case "2":
				new DepositView().init(accountNumber);
				break;
			case "3":
				new WithdrawView().init(accountNumber);
				break;
			case "4":
				new OneCustomerHistoryView().init(accountNumber);
				break;
			case "5":
				new FundTransferView(this).init(accountNumber);
				break;
			case "6":
				break outer;
			case "7":
				exitApp();
			default:
				System.out.println("Invalid option. Please try again.✖️✖️✖️\nRee Eneter Correct Option");
			}
		}
	}

	private void tryAgain(String error) {
		System.out.println(error);
		if(!scannaer.nextLine().equalsIgnoreCase("y")) signinView.init();
		longinCustomerAccount();
	}
}