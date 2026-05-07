package com.zsgs.bankManagement.data.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.dto.DepositORWithdraw;
import com.zsgs.bankManagement.data.dto.Manager;

public class BankDB {

	private static BankDB bankDB;
	private Manager manager;
	private List<Account> account;
	private List<DepositORWithdraw> depositORWithdraw;

	private BankDB() {
		this.account=new ArrayList<>();
		this.depositORWithdraw=new ArrayList<>();
	}
	public static BankDB getInstanse() {
		if (bankDB == null) {
			bankDB = new BankDB();
		}
		return bankDB;
	}
	
	public void init() {}
	
	public List<Account> getCustomerInfo() {
		return  Collections.unmodifiableList(account);
	}

	public void setCustomerInfo(Account accountData) {
		int userId=0;
		if(account.size()==0) {
			userId++;
		}else {
			userId=account.getLast().getUserId();
			userId++;
		}
		accountData.setUserId(userId);
		this.account.add(accountData);
	}
	
	public Manager getManagerRegistrationInfo() {
		return manager;
	}

	public void setRegistrationInfo(Manager manager) {
		this.manager = manager;
	}
	
	public List<DepositORWithdraw> getDepositORWithdrawInfo() {
		return depositORWithdraw;
	}
	
	public List<DepositORWithdraw> getDepositORWithdrawInfo(long accountNo) {
		
		List<DepositORWithdraw> getOneCustomerData=new ArrayList<>();
		
		for(DepositORWithdraw depositORWithdraw:depositORWithdraw) {
			if(depositORWithdraw.getAccountNo()==accountNo) {
				getOneCustomerData.add(depositORWithdraw);
			}
		}
	
		return getOneCustomerData;
	}
	
	public void setdepositORWithdrawInfo(DepositORWithdraw depositORWithdraw) {
		this.depositORWithdraw.add(depositORWithdraw);
	}
	
	public Account getAccount(long accountNumber) {
		
		for (Account accountChak : account) {
			if (accountChak.getAccountNumber() == accountNumber) {
				return accountChak;
			}
		}
		return null;
	}
}