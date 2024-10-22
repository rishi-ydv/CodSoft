package com.rishi.Task3.atm;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ATMInterface {
    public ATMInterface() {
    }

    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList();
        accounts.add(new BankAccount(1000.0));
        accounts.add(new BankAccount(1500.0));
        accounts.add(new BankAccount(2000.0));
        Scanner input = new Scanner(System.in);
        BankAccount userAccount = null;
        ATM atm = null;
        boolean exitATM = false;

        int option;
        while(userAccount == null) {
            System.out.print("Enter your account number: ");
            option = input.nextInt();
            userAccount = findAccount(accounts, option);
            if (userAccount != null) {
                atm = new ATM(userAccount);
                System.out.println("Welcome! Your account number is: " + userAccount.getAccountNo());
            } else {
                System.out.println("Error: Invalid account number. Please try again.");
            }
        }

        while(!exitATM) {
            System.out.println("\nATM menu:");
            System.out.println("1. Check Your Balance ");
            System.out.println("2. Deposit cash ");
            System.out.println("3. Withdraw cash ");
            System.out.println("4. Switch Account ");
            System.out.println("5. Exit ");
            System.out.print("Choose an option: ");
            option = input.nextInt();
            switch (option) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = input.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter the account number to switch to: ");
                    int newAccountNumber = input.nextInt();
                    BankAccount newAccount = findAccount(accounts, newAccountNumber);
                    if (newAccount != null) {
                        atm = new ATM(newAccount);
                        System.out.println("Switched to account number: " + newAccount.getAccountNo());
                    } else {
                        System.out.println("Error: Invalid account number. Unable to switch.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exitATM = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        input.close();
    }

    private static BankAccount findAccount(ArrayList<BankAccount> accounts, int accountNumber) {
        Iterator var2 = accounts.iterator();

        BankAccount account;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            account = (BankAccount)var2.next();
        } while(account.getAccountNo() != (long)accountNumber);

        return account;
    }
}

