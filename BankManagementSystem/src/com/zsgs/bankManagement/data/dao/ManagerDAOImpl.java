package com.zsgs.bankManagement.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zsgs.bankManagement.data.dto.Manager;
import com.zsgs.bankManagement.data.repository.DBConnection;

public class ManagerDAOImpl implements IManagerDAO {

	private Connection connection;
	private PreparedStatement preparedSataement;

	/**
	 * Constructor to initialize the DB connection.
	 */
	public ManagerDAOImpl() {
		try {
			this.connection = DBConnection.getConnctionDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new manager account in the database.
	 *
	 * @param manager the manager object containing details to be created
	 */
	@Override
	public void createAccount(Manager manager) {
		String q = "insert into bank_db(name,password,age,email,mobileNumber) values(?,?,?,?,?)";
		try {

			preparedSataement = connection.prepareStatement(q);
			preparedSataement.setString(1, manager.getName());
			preparedSataement.setString(2, manager.getPassword());
			preparedSataement.setInt(3, manager.getDob());
			preparedSataement.setString(4, manager.getEmailID());
			preparedSataement.setString(5, manager.getMobileNo());
			preparedSataement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retrieves the manager registration information from the database.
	 *
	 * @return the manager information
	 */
	public Manager getManagerRegistrationInfo() {
		Manager manager=new Manager();
		String q = "select *from bank_db";
		ResultSet result = null;
		try {
			preparedSataement = connection.prepareStatement(q);
//			preparedSataement.setString(1, email);
//			preparedSataement.setString(2, password);
			
		
			result = preparedSataement.executeQuery();
			
			if(!result.isBeforeFirst()) return null;
			    result.next();
				manager.setName(result.getString(2));
				manager.setPassword(result.getString(3));
				manager.setDob(result.getInt(4));
				manager.setEmailID(result.getString(5));
				manager.setMobileNo(result.getString(6));
			return manager;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}

	/**
	 * Retrieves a list of all managers.
	 *
	 * @return a list of managers
	 */
	@Override
	public List<Manager> getAllAccounts() {
		return null;
	}

	/**
	 * Updates the account balance for the manager.
	 */
	@Override
	public void updateAccountBalance() {}
}
