package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;
import com.rishi.Task5.studentmgmt.model.Student;

import javax.swing.*;
import java.awt.*;

public class SearchStudentDialog extends JDialog {
    public SearchStudentDialog(JFrame parent, DatabaseHandler dbHandler) {
        super(parent, "Search Student", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Enter Roll No:"));
        JTextField rollNoField = new JTextField();
        panel.add(rollNoField);

        JButton searchButton = new JButton("Search");
        JButton cancelButton = new JButton("Cancel");
        panel.add(searchButton);
        panel.add(cancelButton);

        searchButton.addActionListener(e -> {
            try {
                int rollNo = Integer.parseInt(rollNoField.getText());
                Student student = dbHandler.searchStudent(rollNo);
                if (student != null) {
                    showStudentDetails(student);
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error searching student: " + ex.getMessage());
            }
        });

        cancelButton.addActionListener(e -> dispose());

        add(panel);

    }

    private void showStudentDetails(Student student) {
        JDialog detailsDialog = new JDialog(this, "Student Details", true);
        detailsDialog.setSize(300, 200);
        detailsDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Roll No:"));
        panel.add(new JLabel(String.valueOf(student.getRollNo())));
        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(student.getName()));
        panel.add(new JLabel("Grade:"));
        panel.add(new JLabel(student.getGrade()));
        panel.add(new JLabel("Phone:"));
        panel.add(new JLabel(student.getPhone()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(student.getEmail()));

        detailsDialog.add(panel);
        detailsDialog.setVisible(true);
    }
}