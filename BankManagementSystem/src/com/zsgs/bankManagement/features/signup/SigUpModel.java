package com.zsgs.bankManagement.features.signup;

import java.util.regex.Pattern;

import com.zsgs.bankManagement.data.dao.IManagerDAO;
import com.zsgs.bankManagement.data.dao.ManagerDAOImpl;
import com.zsgs.bankManagement.data.dto.Manager;
import com.zsgs.bankManagement.util.ParseHelper;

class SigUpModel {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");

	private static final int MIN_NAME_LENGTH = 3;
	private static final int MAX_NAME_LENGTH = 50;
	private static final int MIN_AGE_YEARS = 18;
	SigUpView sigUpView;
	
	private IManagerDAO iManagerDAO; 

	SigUpModel(SigUpView sigUpView) {
		this.sigUpView = sigUpView;
		this.iManagerDAO=new ManagerDAOImpl();
	}
	
	void inti() {
		
		if(iManagerDAO.getManagerRegistrationInfo()!=null) {
			sigUpView.errorMassage("\nYour already Registered please go to Sign In!.....\n");
			return;
		}
		
		sigUpView.registerManager();
	}

	void createManager(Manager manager) {
//		BankDB.getInstanse().setRegistrationInfo(manager);
		iManagerDAO.createAccount(manager);
		sigUpView.onRegistrationSuccess("<<<---Registration Successfull!--->>\n\nDo you want to Login? Type Y or N : ");
	}

	String validateName(String nextLine) {
		if (nextLine.trim().isEmpty())
			return "Name cannot be empty";

		if (nextLine.length() < MIN_NAME_LENGTH || nextLine.length() > MAX_NAME_LENGTH)
			return "**Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH
					+ " characters***\n\nEnter valid name:";

		return null;
	}

	String validateEmail(String managerEmail) {

		if (managerEmail == null || managerEmail.trim().isEmpty())
			return "***Email can not be Valid***\n\nRee Enter You Eamil ID:";

		if (!EMAIL_PATTERN.matcher(managerEmail).matches())
			return "Ree Enter a valid email address:";

		return null;
	}

	String validateMobileNumber(String managerMobile) {

		if (managerMobile.trim().isEmpty())
			return "Mobile Number cannot be empty";

		if (!MOBILE_PATTERN.matcher(managerMobile).matches())
			return "***Mobile Number can not be Valid***\n\nRee Enter Your Mobile Number:";
		return null;
	}

	Integer parseDateOfBirth(String dobText) {
		Long dobMillis = ParseHelper.parseDate(dobText);
		if (dobMillis == null)
			return null;
		if (dobMillis >= System.currentTimeMillis())
			return null;
		if (ParseHelper.calculateAgeYears(dobMillis) < MIN_AGE_YEARS)
			return null;
		return  ParseHelper.calculateAgeYears(dobMillis);
	}

	String validatePassWord(String passWord) {

		if (passWord.trim().isEmpty())
			return "Mobile Number cannot be empty";

		if (!PASSWORD_PATTERN.matcher(passWord).matches())
			return "Password must be at least 8 characters and contain letters and numbers\n\nRee Enter Your passWord:";

		return null;
	}

	String validateConfirmPassword(String currentPassword, String confirmPassword) {
		if (confirmPassword == null || !confirmPassword.equals(currentPassword)) 
			return "**password did not match**\nRee Enter Your Confirm Password:";
		return null;
	}
}

//System.out.println("Username and ");
//System.out.println("Do you want to proceed? Type Y or N");
