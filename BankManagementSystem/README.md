# Bank Management System

A robust console-based Bank Management System built with Core Java and MySQL. This application allows users to create accounts, perform transactions (deposits, withdrawals, fund transfers), and manage user data through a secure console interface. The system employs a Model-View-Presenter (MVP) architecture for clean separation of concerns and maintainability.

## Features

- **Account Management**: Create new bank accounts, authenticate users (Sign In / Sign Up), and activate/deactivate accounts.
- **Transactions**: Securely perform deposits, withdrawals, and fund transfers.
- **Transaction History**: View historical records of transactions.
- **Database Integration**: Data persistence using MySQL database with JDBC, leveraging database-level stored procedures to ensure transaction atomicity.
- **MVP Architecture**: Codebase is cleanly organized by separating UI logic (View), business rules (Presenter), and data access (Model).

## Technologies Used

- **Language**: Java (Core Java)
- **Database**: MySQL
- **Database Connectivity**: JDBC (`java.sql`)
- **Architecture**: Model-View-Presenter (MVP)
- **Interface**: Command Line Interface (CLI)

## Project Structure

The project is structured by feature to ensure easy scalability and maintainability:

- `com.zsgs.bankManagement.BankMain`: Application entry point.
- `com.zsgs.bankManagement.data`: Data access layer.
  - `dao`: Data Access Objects containing SQL queries and executing stored procedures.
  - `dto`: Data Transfer Objects representing domain entities like `Account`, `User`, `DepositORWithdraw`.
  - `repository`: DB Connection configuration.
- `com.zsgs.bankManagement.features`: Business logic and UI components, divided by features like `signin`, `signup`, `transactions`, `accountcreation`, etc.
- `com.zsgs.bankManagement.util`: Utility classes like `ConsoleInput` for scanner interactions.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java Development Kit (JDK)**
- **MySQL Server**
- **MySQL Connector/J (JDBC Driver)** (Make sure it is in your classpath, e.g., in the `lib` folder)

## Setup and Installation

1. **Clone the repository or download the source code**.
2. **Database Setup**:
   - Create a database in MySQL named `bank_db`.
   - Update the database credentials (username and password) in `src/com/zsgs/bankManagement/data/repository/DBConnection.java`:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/bank_db";
     private static final String USER = "root";
     private static final String PASSWORD = "your_password"; // Update this to match your MySQL password
     ```
   - The application automatically creates necessary tables (`Account`, `transactions`, `user`, etc.) and stored procedures upon launching.
3. **Compile the project**:
   Use your IDE (Eclipse, IntelliJ, VS Code) to manage the build path and include the JDBC driver in the `lib` folder, or compile manually via terminal.
4. **Run the Application**:
   Execute the `BankMain` class to start the application.

## Usage

When the application starts, you will be greeted by the console interface. Follow the on-screen prompts to:
1. Create a new account or Sign In.
2. Navigate through your dashboard to check your balance.
3. Perform transactions (Deposit, Withdraw, Transfer) and view your transaction history.
