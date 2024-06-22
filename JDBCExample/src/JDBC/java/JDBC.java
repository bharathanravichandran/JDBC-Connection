package JDBC.java;
import java.sql.*;

public class JDBC {

    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3000`/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9445617174";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Creating a statement object using the connection
            stmt = conn.createStatement();
            
            // Creating the table if it does not exist
            createTable(stmt);
            
            // Inserting data into the table
            insertData(stmt, "John Doe", 30, "Software Engineer", 75000.00);
            insertData(stmt, "Jane Smith", 28, "Data Analyst", 60000.00);
            
            // Querying the data from the table
            queryData(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create the 'employees' table
    private static void createTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "name VARCHAR(100)," +
                     "age INT," +
                     "designation VARCHAR(100)," +
                     "salary DECIMAL(10, 2)" +
                     ")";
        stmt.executeUpdate(sql);
        System.out.println("Table 'employees' created (if not existed) successfully");
    }

    // Method to insert data into the 'employees' table
    private static void insertData(Statement stmt, String name, int age, String designation, double salary) throws SQLException {
        String sql = "INSERT INTO employees (name, age, designation, salary) " +
                     "VALUES ('" + name + "', " + age + ", '" + designation + "', " + salary + ")";
        stmt.executeUpdate(sql);
        System.out.println("Data inserted successfully: " + name);
    }

    // Method to query data from the 'employees' table
    private static void queryData(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM employees";
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Fetching data from 'employees' table:");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String designation = rs.getString("designation");
            double salary = rs.getDouble("salary");

            System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Designation: " + designation + ", Salary: " + salary);
        }
        rs.close();
    }
}
