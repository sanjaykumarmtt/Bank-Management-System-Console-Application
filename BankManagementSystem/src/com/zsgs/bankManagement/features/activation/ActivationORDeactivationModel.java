package com.zsgs.bankManagement.features.activation;

import java.util.List;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dao.IAccountDAO;
import com.zsgs.bankManagement.data.dto.Account;

class ActivationORDeactivationModel {

	ActivationORDeactivationView balancEinquiryView;
	List<Account> account;
	IAccountDAO iAccountDAO;

	public ActivationORDeactivationModel(ActivationORDeactivationView balancEinquiryView) {
		this.balancEinquiryView = balancEinquiryView;
//		this.account = BankDB.getInstanse().getCustomerInfo();
		this.iAccountDAO=new AccountDAOImpl();
		this.account=iAccountDAO.getAllAccounts();
	}

	void activation(long accountNumber) {
		boolean checkCondsan=false;
		if(account.isEmpty()) {
			balancEinquiryView.errorMassage("\n<<<-------The Data has not yet been saved in DB------->>>\n");
			return;
		}
		for(Account isTrenOff:account) {
			if(accountNumber==isTrenOff.getAccountNumber()) {
//				isTrenOff.setActive(true);
				iAccountDAO.ActivationORDeactivation(true, accountNumber);
				balancEinquiryView.showMessage("\n<<---Successfully Activation this account!---->>\n");
				checkCondsan=true;
				break;
			}
		}
		if(!checkCondsan) balancEinquiryView.errorMassage("\n<<<-------The data in this Account number : "+accountNumber+" has not yet been saved in DB------->>>\n");
	}
	
	public boolean isOnlyDigits(String accountNumber) {

		for (int i = 0; i < accountNumber.length(); i++) {
			if (!Character.isDigit(accountNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	void deactivation(long accountNumber) {
		boolean checkCondsan=false;
		if(account.isEmpty()) {
			balancEinquiryView.errorMassage("\n<<<-------The Data has not yet been saved in DB------->>>\n");
			return;
		}
		for(Account isTrenOff:account) {
			if(accountNumber==isTrenOff.getAccountNumber()) {
//				isTrenOff.setActive(false);
				iAccountDAO.ActivationORDeactivation(false, accountNumber);
				balancEinquiryView.showMessage("\n<<----Successfully deactivated this account!----->>\n");
				checkCondsan=true;
				break;
			}
		}
		if(!checkCondsan) balancEinquiryView.errorMassage("\n<<<-------The data in this Account number : "+accountNumber+" has not yet been saved in DB------->>>\n");

		}	
}