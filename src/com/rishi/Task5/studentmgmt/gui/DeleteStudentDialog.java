package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentDialog extends JDialog {
    private JTextField rollNoField;
    private DatabaseHandler dbHandler;

    public DeleteStudentDialog(JFrame parent, DatabaseHandler dbHandler) {
        super(parent, "Delete Student", true);
        this.dbHandler = dbHandler;

        initializeDialog(parent);
        createComponents();
    }

    private void initializeDialog(JFrame parent) {
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add roll number input field
        panel.add(new JLabel("Enter Roll No:"));
        rollNoField = new JTextField();
        panel.add(rollNoField);

        // Create buttons
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        // Add action listeners
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add buttons to panel
        panel.add(deleteButton);
        panel.add(cancelButton);

        // Add panel to dialog
        add(panel);
    }

    private void deleteStudent() {
        try {
            int rollNo = Integer.parseInt(rollNoField.getText());
            int confirm = JOptionPane.showConfirmDialog(
                    DeleteStudentDialog.this,
                    "Are you sure you want to delete student with Roll No: " + rollNo + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dbHandler.deleteStudent(rollNo);
                JOptionPane.showMessageDialog(
                        DeleteStudentDialog.this,
                        "Student deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    DeleteStudentDialog.this,
                    "Please enter a valid roll number",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    DeleteStudentDialog.this,
                    "Error deleting student: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}