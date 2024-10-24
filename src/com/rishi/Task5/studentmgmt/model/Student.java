package com.rishi.Task5.studentmgmt.model;

public class Student {
    private int rollNo;
    private String name;
    private String grade;
    private String phone;
    private String email;

    //Constructor to initialize the attribute of an object
    public Student(int rollNo, String name, String grade, String phone, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
        this.phone = phone;
        this.email = email;
    }

    //Getter and Setter to set and get  initialize value of an objects

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
