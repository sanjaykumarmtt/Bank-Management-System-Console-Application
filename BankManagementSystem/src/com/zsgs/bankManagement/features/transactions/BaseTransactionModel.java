package com.zsgs.bankManagement.features.transactions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.zsgs.bankManagement.data.dao.AccountDAOImpl;
import com.zsgs.bankManagement.data.dao.DepositORWithdrawDAO;
import com.zsgs.bankManagement.data.dao.IAccountDAO;
import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.dto.DepositORWithdraw;

public class BaseTransactionModel implements TransactionService {

	private static final DateTimeFormatter FORMATTER = null;
	private DepositORWithdrawDAO depositORWithdrawDAO;
	private IAccountDAO iAccountDAO;
	
	public BaseTransactionModel() {
		this.depositORWithdrawDAO = new  DepositORWithdrawDAO();
		this.iAccountDAO=new AccountDAOImpl();
	}
	@Override
	public boolean isDepositBalance(double depositAmount, Account account) {

		if (depositAmount < 1) {
			return false;
		}
		account.setBalance(account.getBalance() + depositAmount);
		iAccountDAO.updateBalance(account);
		return true;
	}

	@Override
	public boolean isWithdrawBalance(double Withdr, Account account) {

		if (Withdr < 1) {
			return false;
		}
		account.setBalance(account.getBalance() - Withdr);
		return true;
	}

	@Override
	public boolean isValidateAccount(Account account) {

		if (account != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAccountAcctive(Account account) {

		return account.isActive();
	}

	@Override
	public boolean isSufficientBalance(double customerAskMoney, Account account) {

		if (account.getBalance() > customerAskMoney) {
			return true;
		}
		return false;
	}

	public boolean isOnlyDigits(String accountNumber) {

		for (int i = 0; i < accountNumber.length(); i++) {
			if (!Character.isDigit(accountNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void historyTransaction(Account account, String transactType, double depositAmount) {

		DepositORWithdraw depositORWithdraw = new DepositORWithdraw();

		depositORWithdraw.setTransactionId(generateTransactionId());
		depositORWithdraw.setAccountNo(account.getAccountNumber());
		depositORWithdraw.setCustomerName(account.getUserName());
		depositORWithdraw.setTransacAmount(depositAmount);
		depositORWithdraw.setType(account.getAccountType());
		depositORWithdraw.setCurrentbalance(account.getBalance());
		depositORWithdraw.setTransactType(transactType);

		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss a");
		depositORWithdraw.setCurrentTime(LocalTime.now().format(FORMATTER));
		depositORWithdraw.setCurrentDate(LocalDate.now().toString());

		//BankDB.getInstanse().setdepositORWithdrawInfo(depositORWithdraw);
		depositORWithdrawDAO.setDepositORWithdrawORFundTransfer(depositORWithdraw);
		iAccountDAO.updateBalance(account);
	}

	public void fundeTransactionHistory(Account account, String transactType, double transFAmount, String receiverName,
			long receiverAccountNumber) {

		DepositORWithdraw depositORWithdraw = new DepositORWithdraw();

		depositORWithdraw.setTransactionId(generateTransactionId());
		depositORWithdraw.setAccountNo(account.getAccountNumber());
		depositORWithdraw.setCustomerName(account.getUserName());
		depositORWithdraw.setTransacAmount(transFAmount);
		depositORWithdraw.setType(account.getAccountType());
		depositORWithdraw.setCurrentbalance(account.getBalance());
		depositORWithdraw.setTransactType(transactType);
		
		depositORWithdraw.setReceiverName(receiverName);
		depositORWithdraw.setTransacAccountNumber(receiverAccountNumber);

		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss a");
		depositORWithdraw.setCurrentTime(LocalTime.now().format(FORMATTER));
		depositORWithdraw.setCurrentDate(LocalDate.now().toString());

	//	BankDB.getInstanse().setdepositORWithdrawInfo(depositORWithdraw);
		depositORWithdrawDAO.setDepositORWithdrawORFundTransfer(depositORWithdraw);
		
		iAccountDAO.updateBalance(account);
	}
}