package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private DatabaseHandler dbHandler;

    public MainWindow() {
        // Initialize database
        dbHandler = new DatabaseHandler();

        // Set window properties
        setTitle("Student Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons
        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton showAllButton = new JButton("Show All Students");

        // Add action listener for Add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStudentDialog dialog = new AddStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        // Add action listener for Delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteStudentDialog dialog = new DeleteStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        // Add action listener for Search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        // Add action listener for Show All button
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowAllStudentsDialog dialog = new ShowAllStudentsDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);

        // Add panel to frame
        add(buttonPanel);
    }

    public static void main(String[] args) {
        // Create and show window using traditional way
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}