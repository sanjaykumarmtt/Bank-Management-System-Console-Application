package com.zsgs.bankManagement.features.signin;

import java.util.regex.Pattern;

import com.zsgs.bankManagement.data.dao.IManagerDAO;
import com.zsgs.bankManagement.data.dao.ManagerDAOImpl;
import com.zsgs.bankManagement.data.dto.Manager;
import com.zsgs.bankManagement.data.repository.BankDB;

class SigninModel {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	private Manager manager;
	SigninView signinView;
//	private IManagerDAO iManagerDAO;
	

	SigninModel(SigninView signinView) {
		this.signinView = signinView;
//		this.manager = BankDB.getInstanse().getManagerRegistrationInfo();
		this.manager=new ManagerDAOImpl().getManagerRegistrationInfo();
	}

	Manager getDbInstanse() {
		return manager;
	}

	String validateEmailType(String loginMail) {
		if (loginMail == null || loginMail.trim().isEmpty())
			return "***Email can not be Valid***\n\nRee Enter You Eamil ID:";
		if (!EMAIL_PATTERN.matcher(loginMail).matches())
			return "This Email is Not Email Type Ree Enter a valid email address:";
		return null;
	}

	String checkManagerEmail(String email) {
		if(!manager.getEmailID().equals(email)) return "*** This Email ID Not Mach Manager Email ***\n\nRee Enter Email ID:";
		return null;
	}
	
	String validatePassword(String Password) {
		if(!manager.getPassword().equals(Password)) return "** This Password Not Mache**\nDo you want to TryAgain? Type Y or N:";
		return null;
	}
}

