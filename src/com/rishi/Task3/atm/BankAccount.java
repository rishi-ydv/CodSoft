package com.rishi.Task3.atm;

public class BankAccount {
    private static long initialAccountNo = 1000L;
    private long accountNo;
    private double balance;

    public BankAccount(double balance) {
        this.accountNo = (long)(initialAccountNo++);
        this.balance = balance;
    }

    public BankAccount() {
        this.accountNo = (long)(initialAccountNo++);
        this.balance = 0.0;
    }

    public double getBalance() {
        return this.balance;
    }

    public long getAccountNo() {
        return this.accountNo;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}

