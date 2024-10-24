package com.rishi.Task5.studentmgmt.dao;

import com.rishi.Task5.studentmgmt.model.Student;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static final String DATABASE_NAME = "student_db";

    // URL for initial connection without database
    private static final String MYSQL_URL = "jdbc:mysql://" + HOST + ":" + PORT;
    // URL for connection with database
    private static final String DB_URL = MYSQL_URL + "/" + DATABASE_NAME;

    public DatabaseHandler(){
        //Initialize database when create an object of a DatabaseHandler class

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "MySQL JDBC Driver not found: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Initializes the database by performing the following operations:
     * 1. Creates the database if it does not already exist on the local machine.
     * 2. Creates the 'students' table in the database if it does not already exist.
     * 3. Implements robust error handling for SQL exceptions and ensures logging for better debugging and troubleshooting.
     * 4. Ensures proper resource management by closing database connections and statements to prevent resource leaks.
     *
     * Error Handling:
     * - Catches SQLExceptions and logs relevant error messages.
     * - Ensures the method remains robust even if the database or table creation fails.
     *
     * Resource Management:
     * - Closes the `Statement` and `Connection` objects in a `finally` block to prevent potential memory/resource leaks.
     *
     * Notes:
     * - Assumes that the database connection properties (URL, USER, PASSWORD) are valid and provided externally.
     * - This method is void and does not return any value.
     *
     * Possible Enhancements:
     * - Consider adding logging to a file instead of printing errors to `System.err` for better production readiness.
     * - Optionally, allow the database name and table name to be passed as parameters for more flexibility.
     *
     * @throws SQLException if a database access error occurs
     */



    private void initializeDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Create database if not exists
            conn = DriverManager.getConnection(MYSQL_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);

            // Close previous statement, but reuse the connection for the next operation
            stmt.close();

            // Reconnect to the newly created database and create table if not exists
            String dbUrlWithDatabase = MYSQL_URL + "/" + DATABASE_NAME; // Corrected connection URL
            conn = DriverManager.getConnection(dbUrlWithDatabase, USER, PASSWORD);
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "roll_no INT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "grade VARCHAR(10) NOT NULL," +
                    "phone VARCHAR(15) NOT NULL," +
                    "email VARCHAR(100) NOT NULL)";
            stmt.executeUpdate(createTableSQL);

            // Show a success message
            JOptionPane.showMessageDialog(null, "Database and table creation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            // Show an error message dialog if an exception occurs
            JOptionPane.showMessageDialog(null, "Database initialization failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("Database initialization failed: " + e.getMessage());
        } finally {
            // Ensure both the statement and connection are closed to prevent resource leaks
            closeResources(stmt, conn);
        }
    }

    private Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw e;
        }
    }

    public void addStudent(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO students (roll_no, name, grade, phone, email) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.getRollNo());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGrade());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getEmail());
            pstmt.executeUpdate();


        } finally {
            closeResources(pstmt, conn);
        }
    }

    public void deleteStudent(int rollNo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "DELETE FROM students WHERE roll_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rollNo);
            int rowsAffected = pstmt.executeUpdate();



        }  finally {
            closeResources(pstmt, conn);
        }
    }

    public Student searchStudent(int rollNo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM students WHERE roll_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rollNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            return null;
        } finally {
            closeResultSet(rs);
            closeResources(pstmt, conn);
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                students.add(student);
            }

            return students;
        } finally {
            closeResultSet(rs);
            closeResources(stmt, conn);
        }
    }

    private void closeResources(Statement stmt, Connection conn) {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    private void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
    }
}


