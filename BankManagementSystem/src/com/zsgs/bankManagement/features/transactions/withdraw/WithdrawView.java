package com.zsgs.bankManagement.features.transactions.withdraw;

import java.util.Scanner;

import com.zsgs.bankManagement.util.ConsoleInput;

public class WithdrawView implements IWithdrawView {

	private IWithdrawPresenter iWithdrawPresenter;
	private Scanner scanner;

	public WithdrawView() {
		this.iWithdrawPresenter = new WithdrawPresenter(this);
		this.scanner = ConsoleInput.getScanner();
	}

	@Override
	public void init(String accountNumber) {
		Message("<<<--------Customer Deposit Page------->>>");
		iWithdrawPresenter.init(accountNumber);
	}

	@Override
	public void withdrawAmont() {
		Message("Eneter withdraw Mone : ");
		String amount = "";
		do {
			amount = scanner.nextLine();
			if (!iWithdrawPresenter.isOnlyDigits(amount))
				showErrorMessage("Ree Eneter Valied Amonte : ");
			else {
				break;
			}
		} while (true);
		Message(iWithdrawPresenter.withdraw(Double.parseDouble(amount)));
	}

	private void Message(String message) {
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
