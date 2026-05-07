package com.zsgs.bankManagement.features.customer.detail;

import java.util.List;

import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.dto.Branch;
import com.zsgs.bankManagement.features.base.BaseView;
import com.zsgs.bankManagement.features.managerhome.ManagerHomeView;

public class DetailView extends BaseView{

	private DetailModel detailModel;
	private ManagerHomeView managerHomeView;

	public DetailView(ManagerHomeView managerHomeView) {
		this.detailModel = new DetailModel(this);
		this.managerHomeView=managerHomeView;
	}

	public void init() {
		displayCustonerDetail();
	}

	private void displayCustonerDetail() {
		List<Account> customerData = detailModel.getCustomerData();
		if(customerData!=null) {
			System.out.println("\n<<-------SBI All Customer Data------>>");
		Branch branch=customerData.get(0).getBranch();
		System.out.printf("%-5s %-20s %-15s %-15s %-10s %-15s %-15s %-12s %n","ID", "AccountNumber", "balance","AccountType","isActive","CustomerName","Address","PhoneNumber");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		for(Account accountData:customerData) {
			System.out.printf("%-5d %-20d %-15.2f %-16s %-10b %-15s %-15s %-12s %n",accountData.getUserId(), accountData.getAccountNumber(), accountData.getBalance(),accountData.getAccountType(),accountData.isActive(),accountData.getUserName(),accountData.getAddress(),accountData.getPhoneNumber());
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------\n\n");
		System.out.println("<---Bank Branch Detail--->");
		System.out.printf("%-19s %-20s %-15s %-48s %-10s %n","ifscCode", "MicrCode", "BranchName","Address","City");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-19s %-20s %-15s %-48s %-10s %n",branch.getIfscCode() ,branch.getMicrCode(),branch.getBranchName(),branch.getAddress(),branch.getCity());
		
		}else {
			showMessage("\n<----Customer data not yet saved---->\n");
		}
		managerHomeView.init();
	}
}
