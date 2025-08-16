import java.sql.*;
import java.util.Scanner;

public class Transaction {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";

    private  static final String username = "root";

    private static final String password = "@ani.8727M";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            // 2. Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);  // transaction control

            // 3. Ask user for operation
            System.out.println("==== Banking Transaction System ====");
            System.out.println("1. Credit (Deposit Money)");
            System.out.println("2. Debit (Withdraw Money)");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            System.out.print("Enter Account Number: ");
            int accountNumber = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            // Show balance before transaction
            double oldBalance = getBalance(connection, accountNumber);
            System.out.println("Current Balance: " + oldBalance);


            boolean success = false;

            if (choice == 1) { // CREDIT
                success = creditAmount(connection, accountNumber, amount);
            } else if (choice == 2) { // DEBIT
                if (isSufficient(connection, accountNumber, amount)) {
                    success = debitAmount(connection, accountNumber, amount);
                } else {
                    System.out.println("Insufficient Balance. Transaction Cancelled.");
                }
            } else {
                System.out.println("Invalid choice.");
            }

            // Commit or rollback transaction
            if (success) {
                connection.commit();
                double newBalance = getBalance(connection, accountNumber);
                System.out.println("Transaction Successful!");
                System.out.println("Updated Balance: " + newBalance);
            } else {
                connection.rollback();
                System.out.println("Transaction Failed!");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Debit operation
    private static boolean debitAmount(Connection connection, int accountNumber, double amount) {
        try {
            String query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, accountNumber);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in Debit: " + e.getMessage());
        }
        return false;
    }

    // Credit operation
    private static boolean creditAmount(Connection connection, int accountNumber, double amount) {
        try {
            String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, accountNumber);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in Credit: " + e.getMessage());
        }
        return false;
    }

    // Check sufficient balance before debit
    private static boolean isSufficient(Connection connection, int accountNumber, double amount) {
        double balance = getBalance(connection, accountNumber);
        return balance >= amount;
    }

    // Fetch account balance
    private static double getBalance(Connection connection, int accountNumber) {
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching balance: " + e.getMessage());
        }
        return 0.0;
    }
}