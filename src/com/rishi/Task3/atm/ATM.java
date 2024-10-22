package com.rishi.Task3.atm;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (amount > this.account.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            this.account.withdraw(amount);
            System.out.println("Withdraw successful! Your new balance is: " + this.account.getBalance());
        }

    }

    public void deposit(double amount) {
        if (amount <= 0.0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            this.account.deposit(amount);
            System.out.println("Deposit successful! Your new balance is: " + this.account.getBalance());
        }

    }

    public void checkBalance() {
        System.out.println("Account Number: " + this.account.getAccountNo());
        System.out.println("Your current balance is: " + this.account.getBalance());
    }
}
