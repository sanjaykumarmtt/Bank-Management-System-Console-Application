package com.zsgs.bankManagement.features.transactions.deposit;

import java.util.Scanner;

import com.zsgs.bankManagement.util.ConsoleInput;
public class DepositView implements IDepositView{
	
	private IDepositPresenter iDepositPresenter;
	private Scanner scanner;
	
	public DepositView() {
		this.iDepositPresenter =new DepositPresenter(this);
		this.scanner=ConsoleInput.getScanner();
	}

	@Override
	public void init(String accountNumber) {
		message("<<<--------Customer Deposit Page------->>>");
		iDepositPresenter.init(accountNumber);
	}
	@Override
	public void depositAmont() {
		message("Eneter Deposit Money : ");
		String amount="";
		do {
			amount=scanner.nextLine();
			if(!iDepositPresenter.isOnlyDigits(amount)) showErrorMessage("Re-Enter Valid Amount : ");
			else {
				break;
			}
		}while(true);
		message(iDepositPresenter.depositBalance(Double.parseDouble(amount)));
	}
	
	private void message(String message) {
		System.out.println(message);
	}

	@Override
	public void onSuccessFailed(String message) {
		System.out.println(message);
	}

	@Override
	public void showErrorMessage(String message) {
		System.out.println(message);
	}
}
