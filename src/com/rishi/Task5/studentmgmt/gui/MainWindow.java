package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private DatabaseHandler dbHandler;

    public MainWindow() {
        initializeWindow();
        initializeDatabase();
        createButtons();
    }

    private void initializeWindow() {
        setTitle("Student Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeDatabase() {
        dbHandler = new DatabaseHandler();
    }

    private void createButtons() {
        // Create main panel with grid layout
        JPanel buttonPanel = createButtonPanel();

        // Create buttons
        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton showAllButton = new JButton("Show All Students");

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStudentDialog dialog = new AddStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteStudentDialog dialog = new DeleteStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(MainWindow.this, dbHandler);
                dialog.setVisible(true);
            }
        });

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

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    public static void main(String[] args) {
        // Create and show window on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
