package com.zsgs.bankManagement.features.accountcreation;

import java.util.Random;
import java.util.regex.Pattern;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dao.IAccountDAO;
import com.zsgs.bankManagement.data.dto.Account;

class AccountCreationModel {
	
	//private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
	//private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");

	private static final int MIN_NAME_LENGTH = 3;
	private static final int MAX_NAME_LENGTH = 50;
	//	private static final int MIN_AGE_YEARS = 18;

	AccountCreationView accountCreationView;
	private final static int prefix = 9102;
//	private BankDB db;
	
	private IAccountDAO iAccountDAO;

	public AccountCreationModel(AccountCreationView accountCreationView) {
		this.accountCreationView = accountCreationView;
//		this.db=BankDB.getInstanse();
		this.iAccountDAO= new AccountDAOImpl();
	}
	
	 void createManager(Account account) {
		 if(account!=null) {
//		 db.setCustomerInfo(account);
			 iAccountDAO.createAccount(account);
		 accountCreationView.onCustomerAccountCreated("<<---Account Cretion successfully finished! Account Number is "+account.getAccountNumber()+"--->>\n\nDo you want to another account create? Type Y or N: ");
		 }
		 else 
		 accountCreationView.errorMassage("User Data is Emtey");
	}

	long generateAccountNumber() {
		Random random = new Random();
		long sb = prefix;
		for (int i = 0; i < 8; i++) {
			int digit = random.nextInt(10);
			sb=(sb*10)+digit;
		}
		return sb;
	}
	
	String getAccountType(String option) {
		switch (option) {
		case "1":
			return "Savings";
		case "2":
			return "Current";

		default:
			accountCreationView.errorMassage("Invalid option. Please try again.\n1.Savings\n2.Current");
		}
		return null;
	}
	Object isAccountActivationRODeactivation(String option) {
		switch (option) {
		case "1":
			return true;
		case "2":
			return false;

		default:
			accountCreationView.errorMassage("Invalid option. Please try again.\n1.Activation\n2.Deactivation");
		}
		return null;
	}
	
	String validateName(String nextLine) {
		if (nextLine.trim().isEmpty())
			return "Name cannot be empty";

		if (nextLine.length() < MIN_NAME_LENGTH || nextLine.length() > MAX_NAME_LENGTH)
			return "**Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH
					+ " characters***\n\nEnter valid name:";
		return null;
	}
	
	String validateMobileNumber(String customerMobile) {

		if (customerMobile.trim().isEmpty())
			return "Mobile Number cannot be empty";

		if (!MOBILE_PATTERN.matcher(customerMobile).matches())
			return "***Mobile Number can not be Valid***\n\nRee Enter Your Mobile Number:";
		return null;
	}
}
