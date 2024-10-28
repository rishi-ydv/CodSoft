package com.rishi.Task5.studentmgmt.gui;

import com.rishi.Task5.studentmgmt.dao.DatabaseHandler;
import com.rishi.Task5.studentmgmt.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowAllStudentsDialog extends JDialog {
    public ShowAllStudentsDialog(JFrame parent, DatabaseHandler dbHandler) {
        super(parent, "All Students", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        try {
            String[] columns = {"Roll No", "Name", "Grade", "Phone", "Email"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable table = new JTable(model);

            for (Student student : dbHandler.getAllStudents()) {
                model.addRow(new Object[]{
                        student.getRollNo(),
                        student.getName(),
                        student.getGrade(),
                        student.getPhone(),
                        student.getEmail()
                });
            }

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeButton);

            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }

        //setVisible(true);
    }
}