package com.zsgs.bankManagement.features.transactions.fundtransfer;

import java.util.Scanner;

import com.zsgs.bankManagement.features.customerlogin.CustomerLoginView;
import com.zsgs.bankManagement.util.ConsoleInput;

public class FundTransferView implements IFundTransferView {

	private IFundtransferPresenter iFundtransferPresenter;
	private Scanner scanner;
	private CustomerLoginView customerLoginView;
	private String accountNumber;

	public FundTransferView(CustomerLoginView customerLoginView) {
		this.iFundtransferPresenter = new FundTransferPresenter(this);
		this.scanner = ConsoleInput.getScanner();
		this.customerLoginView = customerLoginView;
	}

	@Override
	public void init(String accountNumber) {
		this.accountNumber = accountNumber;
		iFundtransferPresenter.init(accountNumber);

	}

	@Override
	public void validateAmountANDREAccountNumber() {
		String amount = "";
		Message("<<<------------Welcom To FundTransFer---------->>>\nEneter The Amount : ");
		outer: do {
			amount = scanner.nextLine();
			String error1 = iFundtransferPresenter.numberValidation(amount);

			if (error1 != null) {
				showErrorMessage(error1);
			} else if (error1 == null) {
				String error2 = iFundtransferPresenter.amountIsAvlabel(amount);

				if (error2 != null) {
					showErrorMessageGoBackROStant(error2,"Ree Eneter The Amount : ");
				} else {
					break outer;
				}
			}
		} while (true);

		

		String toAccountNumber = "";
		Message("Receiver account number : ");

 outer :do {
			toAccountNumber = scanner.nextLine();
			String error = iFundtransferPresenter.numberValidation(toAccountNumber);
			if (error != null)
				showErrorMessage(error);
			else if(error== null) {
				String error2=iFundtransferPresenter.receiverAccountIsAvlabel(toAccountNumber);
				if(error2!=null) {
					showErrorMessageGoBackROStant(error2,"Ree Receiver account number : ");
				}else {
					break outer;	
				}
				
			}
				
		} while (true);

 			fundTransFer(amount,toAccountNumber);

	}

	private void fundTransFer(String amount, String toAccountNumber) {
		iFundtransferPresenter.getAmounttoGive(amount, toAccountNumber);
	}




	@Override
	public void Message(String message) {
		System.out.println(message);
//		customerLoginView.customerMenu(accountNumber);
	}

	@Override
	public void onSuccessFailed(String message) {
		System.out.println(message);
	}

	@Override
	public void showErrorMessage(String message) {
		System.out.println(message);
	}

	public void showErrorMessageGoBackROStant(String message,String massage1) {
		System.out.println(message);
		if (!scanner.nextLine().contentEquals("Y"))
			customerLoginView.customerMenu(accountNumber);
		System.out.println(massage1);
	}

}
