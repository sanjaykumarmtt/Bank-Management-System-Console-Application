package com.zsgs.bankManagement.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.bankManagement.data.dto.DepositORWithdraw;
import com.zsgs.bankManagement.data.repository.DBConnection;

public class DepositORWithdrawDAO implements IDepositORWithdrawDAO {

	private Connection connection;
	private PreparedStatement preparedSataement;

	/**
	 * Constructor to initialize DB connection and create necessary tables/procedures.
	 */
	public DepositORWithdrawDAO() {
		try {
			this.connection = DBConnection.getConnctionDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createTable();
		createMysqlProcedureFunction();
	}

	/**
	 * Creates the transactions table if it doesn't already exist.
	 */
	void createTable() {
		String q = "CREATE TABLE If not exists transactions(" + "    id INT NOT NULL AUTO_INCREMENT,"
				+ "    transactionId VARCHAR(25)," + "    accountId INT," + "    currentbalance DOUBLE,"
				+ "    transacAmount DOUBLE," + "    transactType VARCHAR(20),"
				+ "    transacAccountNumber VARCHAR(15)," + "    currentTime VARCHAR(30),"
				+ "    currentDate VARCHAR(30)," + "    PRIMARY KEY (id)," + "    INDEX (accountId),"
				+ "    CONSTRAINT fk_account" + "        FOREIGN KEY (accountId)" + "        REFERENCES Account(id)"
				+ ");";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the MySQL stored procedure for recording deposits and withdrawals.
	 */
	void createMysqlProcedureFunction() {
		String ProcedureFunctionQuery = "create procedure If not exists sp_DepositORWithdraw("
				+ "IN tr_Id varchar(20),"
				+ "IN ac_Number varchar(15),"
				+ "IN cr_balance double,"
				+ "IN tr_Amount double,"
				+ "IN tr_Type varchar(20),"
				+ "IN tr_AccountNumber varchar(15),"
				+ "IN cur_Time varchar(30),"
				+ "IN cur_Date varchar(30))"
				+ "BEGIN"
				+ "  DECLARE v_userId INT;"
				+ "  DECLARE EXIT HANDLER FOR SQLEXCEPTION"
				+ "    BEGIN"
				+ "       ROLLBACK;"
				+ "    END;"
				+ "SET v_userId = (select id from Account where accountNumber=ac_Number);"
				+ "Insert into transactions(transactionId,accountId,currentbalance,transacAmount,transactType,transacAccountNumber,currentTime,currentDate)"
				+ "                 values(tr_Id,v_userId,cr_balance,tr_Amount,tr_Type,tr_AccountNumber,cur_Time,cur_Date);"
				+ "COMMIT;"
				+ "END   ";

		try {
			preparedSataement = connection.prepareStatement(ProcedureFunctionQuery);
			preparedSataement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Records a deposit, withdrawal, or fund transfer transaction using a stored procedure.
	 *
	 * @param depositORWithdraw the transaction details object
	 */
	@Override
	public void setDepositORWithdrawORFundTransfer(DepositORWithdraw depositORWithdraw) {
		String q = "CALL sp_DepositORWithdraw(?,?,?,?,?,?,?,?)";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setString(1, depositORWithdraw.getTransactionId());
			preparedSataement.setLong(2, depositORWithdraw.getAccountNo());
			preparedSataement.setDouble(3, depositORWithdraw.getCurrentbalance());
			preparedSataement.setDouble(4, depositORWithdraw.getTransacAmount());
			preparedSataement.setString(5, depositORWithdraw.getTransactType());
			preparedSataement.setLong(6, depositORWithdraw.getTransacAccountNumber());

			preparedSataement.setString(7, depositORWithdraw.getCurrentTime());

			preparedSataement.setString(8, depositORWithdraw.getCurrentDate());
			preparedSataement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the transaction history for a specific account.
	 *
	 * @param accountNumber the account number to fetch transactions for
	 * @return a list of transactions associated with the account
	 */
	@Override
	public List<DepositORWithdraw> getOneDepositORWithdraw(long accountNumber) {

		List<DepositORWithdraw> alllistDWTD = new ArrayList<>();

		String q = "SELECT " + "u.userName AS senderName, " + "a.accountNumber AS senderAccountNumber, "
				+ "a.accountType AS senderAccountType, " + "u2.userName AS recipientName, " + "t.* " + "FROM user u "
				+ "JOIN Account a ON u.userID = a.userID " + "JOIN transactions t ON a.id = t.accountId "
				+ "LEFT JOIN Account a2 ON t.transacAccountNumber = a2.accountNumber "
				+ "LEFT JOIN user u2 ON a2.userID = u2.userID where a.accountNumber=?;";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setLong(1, accountNumber);

			ResultSet outPut = preparedSataement.executeQuery();
			while (outPut.next()) {
				DepositORWithdraw depositORWithdraw = new DepositORWithdraw();

				depositORWithdraw.setTransactionId(outPut.getString("transactionId"));
				depositORWithdraw.setAccountNo(outPut.getLong("senderAccountNumber"));
				depositORWithdraw.setCustomerName(outPut.getString("senderName"));
				depositORWithdraw.setTransacAmount(outPut.getDouble("transacAmount"));
				depositORWithdraw.setType(outPut.getString("senderAccountType"));
				depositORWithdraw.setCurrentbalance(outPut.getDouble("currentbalance"));
				depositORWithdraw.setTransactType(outPut.getString("transactType"));

				depositORWithdraw.setReceiverName(outPut.getString("recipientName"));
				depositORWithdraw.setTransacAccountNumber(outPut.getLong("transacAccountNumber"));

				depositORWithdraw.setCurrentTime(outPut.getString("currentTime"));
				depositORWithdraw.setCurrentDate(outPut.getString("currentDate"));
				alllistDWTD.add(depositORWithdraw);
			}
			return alllistDWTD;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Retrieves all transactions recorded in the system.
	 *
	 * @return a list of all transactions
	 */
	@Override
	public List<DepositORWithdraw> getAllDepositORWithdraw() {
		List<DepositORWithdraw> alllistDWTD = new ArrayList<>();

		String q = "SELECT " + "u.userName AS senderName, " + "a.accountNumber AS senderAccountNumber, "
				+ "a.accountType AS senderAccountType, " + "u2.userName AS recipientName, " + "t.* " + "FROM user u "
				+ "JOIN Account a ON u.userID = a.userID " + "JOIN transactions t ON a.id = t.accountId "
				+ "LEFT JOIN Account a2 ON t.transacAccountNumber = a2.accountNumber "
				+ "LEFT JOIN user u2 ON a2.userID = u2.userID;";

		try {
			preparedSataement = connection.prepareStatement(q);
			ResultSet outPut = preparedSataement.executeQuery();
			while (outPut.next()) {
				DepositORWithdraw depositORWithdraw = new DepositORWithdraw();

				depositORWithdraw.setTransactionId(outPut.getString("transactionId"));
				depositORWithdraw.setAccountNo(outPut.getLong("senderAccountNumber"));
				depositORWithdraw.setCustomerName(outPut.getString("senderName"));
				depositORWithdraw.setTransacAmount(outPut.getDouble("transacAmount"));
				depositORWithdraw.setType(outPut.getString("senderAccountType"));
				depositORWithdraw.setCurrentbalance(outPut.getDouble("currentbalance"));
				depositORWithdraw.setTransactType(outPut.getString("transactType"));

				depositORWithdraw.setReceiverName(outPut.getString("recipientName"));
				depositORWithdraw.setTransacAccountNumber(outPut.getLong("transacAccountNumber"));

				depositORWithdraw.setCurrentTime(outPut.getString("currentTime"));
				depositORWithdraw.setCurrentDate(outPut.getString("currentDate"));
				alllistDWTD.add(depositORWithdraw);
			}
			return alllistDWTD;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
