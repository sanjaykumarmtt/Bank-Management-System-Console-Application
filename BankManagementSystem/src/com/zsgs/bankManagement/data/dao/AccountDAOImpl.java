package com.zsgs.bankManagement.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.bankManagement.data.dto.Account;
import com.zsgs.bankManagement.data.repository.DBConnection;

public class AccountDAOImpl implements IAccountDAO {

	private Connection connection;
	private PreparedStatement preparedSataement;

	/**
	 * Constructor to initialize DB connection and create necessary tables/procedures.
	 */
	public AccountDAOImpl() {
		try {
			this.connection = DBConnection.getConnctionDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createTable();
		createMysqlProcedureFunction();
	}
	
	/**
	 * Creates the Account table if it doesn't already exist.
	 */
	void createTable(){
		String q="CREATE TABLE If not exists Account("
				+ "    id INT NOT NULL AUTO_INCREMENT,"
				+ "    userID INT,"
				+ "    accountNumber VARCHAR(15),"
				+ "    balance DOUBLE,"
				+ "    accountType VARCHAR(20),"
				+ "    isActive TINYINT(1),"
				+ "    PRIMARY KEY (id),"
				+ "		FOREIGN KEY (userID) REFERENCES user(userID)"
				+ ");";
		
	
		
		
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Creates the MySQL stored procedure for creating a user and an account.
	 */
	void createMysqlProcedureFunction() {
		String ProcedureFunctionQuery="CREATE PROCEDURE If not exists sp_CreateUserAndAccount("
				+ "    IN p_userName VARCHAR(20),"
				+ "    IN p_address VARCHAR(20),"
				+ "    IN p_phoneNumber VARCHAR(20),"
				+ "    IN p_accountNumber VARCHAR(15),"
				+ "    IN p_balance DOUBLE,"
				+ "    IN p_accountType VARCHAR(20),"
				+ "    IN p_isActive TINYINT(1))"
				+ "BEGIN"
				+ "    DECLARE v_userId INT;"
				+ "    DECLARE EXIT HANDLER FOR SQLEXCEPTION"
				+ "    BEGIN"
				+ "        ROLLBACK;"
				+ "    END;"
				+ "    INSERT INTO user (userName, address, phoneNumber) VALUES (p_userName, p_address, p_phoneNumber);"
				+ "    SET v_userId = LAST_INSERT_ID();"
				+ "    INSERT INTO Account(userID, accountNumber, balance, accountType, isActive) VALUES (v_userId, p_accountNumber, p_balance, p_accountType, p_isActive);"
				+ "    COMMIT;"
				+ "END";
		try {
			preparedSataement = connection.prepareStatement(ProcedureFunctionQuery);
			preparedSataement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

	/**
	 * Creates a new account using a stored procedure.
	 *
	 * account the account object containing details to be created
	 */
	@Override
	public void createAccount(Account account) {

//		String q = "call sp_CreateUserAndAccount(customerName,address,phoneNumber,accountNumber,balance,accountType,isActive) values(?,?,?,?,?,?,?)";
		String q = "CALL sp_CreateUserAndAccount(?, ?, ?, ?, ?, ?, ?)";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setString(1, account.getUserName());
			preparedSataement.setString(2, account.getAddress());
			preparedSataement.setString(3, account.getPhoneNumber());
			preparedSataement.setLong(4, account.getAccountNumber());
			preparedSataement.setDouble(5, account.getBalance());
			preparedSataement.setString(6, account.getAccountType());
			preparedSataement.setBoolean(7, account.isActive());
			preparedSataement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Updates the balance of a specific account in the database.
	 *
	 * @param account the account object containing the updated balance and account number
	 */
	public void updateBalance(Account account) {
		String q = "update  Account set balance=? where accountNumber=?";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setDouble(1,account.getBalance());
			preparedSataement.setLong(2, account.getAccountNumber());
			preparedSataement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Retrieves the registration information for a specific account.
	 *
	 * @param accountNumber the account number to fetch details for
	 * @return the account information, or null if not found
	 */
	@Override
	public Account getAccountsRegistrationInfo(long accountNumber) {
		Account account = new Account();
//		String q = "Select *from Account where accountNumber=?";
		String q = "select u.userID,u.userName,u.address,u.phoneNumber,a.accountNumber,a.balance,a.accountType,a.isActive from user u join Account a on u.userID=a.userID where a.accountNumber=?";
		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setLong(1, accountNumber);

			ResultSet result = preparedSataement.executeQuery();
			if (!result.isBeforeFirst())
				return null;

			result.next();
			account.setUserId(result.getInt("userID"));
			account.setUserName(result.getString("userName"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phoneNumber"));

			account.setAccountNumber(result.getLong("accountNumber"));
			account.setBalance(result.getDouble("balance"));
			account.setAccountType(result.getString("accountType"));
			account.setActive(result.getBoolean("isActive"));

			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves a list of all accounts registered in the database.
	 *
	 * @return a list of all accounts, or null if empty
	 */
	@Override
	public List<Account> getAllAccounts() {
		List<Account> list = new ArrayList<>();
//		String q = "Select *from Account";
		String q = "select u.userID,u.userName,u.address,u.phoneNumber,a.accountNumber,a.balance,a.accountType,a.isActive from user u join Account a on u.userID=a.userID";
		try {
			preparedSataement = connection.prepareStatement(q);
			ResultSet result = preparedSataement.executeQuery();

			if (!result.isBeforeFirst())
				return null;

			while (result.next()) {
				Account account = new Account();
				account.setUserId(result.getInt("userID"));
				account.setUserName(result.getString("userName"));
				account.setAddress(result.getString("address"));
				account.setPhoneNumber(result.getString("phoneNumber"));

				account.setAccountNumber(result.getLong("accountNumber"));
				account.setBalance(result.getDouble("balance"));
				account.setAccountType(result.getString("accountType"));
				account.setActive(result.getBoolean("isActive"));
				list.add(account);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Activates or deactivates an account in the database.
	 *
	 * @param isCon         true to activate, false to deactivate
	 * @param accountNumber the account number to update
	 */
	@Override
	public void ActivationORDeactivation(boolean isCon, long accountNumber) {
		String q = "Update Account set isActive=? where accountNumber=?";

		try {
			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setBoolean(1, isCon);
			preparedSataement.setLong(2, accountNumber);
			preparedSataement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}