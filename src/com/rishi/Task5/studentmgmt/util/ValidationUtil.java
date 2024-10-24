package com.rishi.Task5.studentmgmt.util;

public class ValidationUtil {
    public static boolean isValidRollNo(String rollNo) {
        try {
            int roll = Integer.parseInt(rollNo);
            return roll > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }
}
