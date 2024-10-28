package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;
import com.rishi.Task5.studentmgmt.model.Student;
import com.rishi.Task5.studentmgmt.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

public class AddStudentDialog extends JDialog {
    private JTextField rollNoField, nameField, gradeField, phoneField, emailField;

    public AddStudentDialog(JFrame parent, DatabaseHandler dbHandler) {
        super(parent, "Add Student", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Roll No:"));
        rollNoField = new JTextField();
        panel.add(rollNoField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        panel.add(gradeField);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        panel.add(saveButton);
        panel.add(cancelButton);

        saveButton.addActionListener(e -> {
            if (validateInputs()) {
                try {
                    Student student = new Student(
                            Integer.parseInt(rollNoField.getText()),
                            nameField.getText(),
                            gradeField.getText(),
                            phoneField.getText(),
                            emailField.getText()
                    );
                    dbHandler.addStudent(student);
                    JOptionPane.showMessageDialog(this, "Student added successfully!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(e -> dispose());

        add(panel);

    }

    private boolean validateInputs() {
        if (!ValidationUtil.isValidRollNo(rollNoField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number!");
            return false;
        }
        if (!ValidationUtil.isValidName(nameField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Name!");
            return false;
        }
        if (!ValidationUtil.isValidPhone(phoneField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Phone Number!");
            return false;
        }
        if (!ValidationUtil.isValidEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Email!");
            return false;
        }
        return true;
    }
}