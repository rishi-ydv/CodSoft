package com.rishi.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {
    public StudentGradeCalculator() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> marks = new ArrayList();
        int totalMarks = 0;
        System.out.print("Enter the number of Subject: ");
        int subjects = input.nextInt();

        for(int i = 0; i < subjects; ++i) {
            System.out.print("Enter the marks of  subject " + (i + 1) + " : ");
            int mark = input.nextInt();
            if (mark >= 0 && mark <= 100) {
                marks.add(mark);
                totalMarks += mark;
            } else {
                System.out.println("Please enter marks in Range of (0 - 100) only ");
                --i;
            }
        }

        double avgPercentage = avgCalculate(totalMarks, subjects);
        String grade;
        if (avgPercentage >= 90.0) {
            grade = "A+";
        } else if (avgPercentage >= 80.0) {
            grade = "A";
        } else if (avgPercentage >= 70.0) {
            grade = "B+";
        } else if (avgPercentage >= 60.0) {
            grade = "B";
        } else if (avgPercentage >= 50.0) {
            grade = "C+";
        } else if (avgPercentage >= 40.0) {
            grade = "C";
        } else if (avgPercentage >= 30.0) {
            grade = "D";
        } else {
            grade = "F";
        }

        String totalMarksLine = "Total Marks: " + totalMarks;
        String avgPercentageLine = String.format("Average Percentage: %.2f%%", avgPercentage);
        String gradeLine = "Grade: " + grade;
        int maxLength = Math.max(totalMarksLine.length(), Math.max(avgPercentageLine.length(), gradeLine.length()));
        String dashLine = (new String(new char[maxLength + 6])).replace("\u0000", "-");
        System.out.println(dashLine);
        System.out.printf("|  %-" + maxLength + "s  |\n", totalMarksLine);
        System.out.printf("|  %-" + maxLength + "s  |\n", avgPercentageLine);
        System.out.printf("|  %-" + maxLength + "s  |\n", gradeLine);
        System.out.println(dashLine);
    }

    public static double avgCalculate(int totalMarks, int noOfSubjects) {
        return (double)totalMarks / (double)noOfSubjects;
    }
}
