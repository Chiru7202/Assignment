package com.training;
import java.util.Scanner;

	public class BankAccount {
	    private String accountHolderName;
	    private double balance;

	    // Constructor to initialize account holder's name
	    public BankAccount(String accountHolderName) {
	        this.accountHolderName = accountHolderName;
	        this.balance = 0.0; // Initial balance is 0
	    }

	    // Method to deposit money
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposited: " + amount);
	        } else {
	            System.out.println("Deposit amount must be greater than zero.");
	        }
	    }

	    // Method to withdraw money
	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: " + amount);
	        } else if (amount > balance) {
	            System.out.println("Insufficient balance.");
	        } else {
	            System.out.println("Withdrawal amount must be greater than zero.");
	        }
	    }

	    // Method to display the balance
	    public void showBalance() {
	        System.out.println("Account Holder: " + accountHolderName);
	        System.out.println("Current Balance: " + balance);
	    }

	    // Main method
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter account holder's name:");
	        String name = scanner.nextLine();

	        BankAccount account = new BankAccount(name);
            
	        // Menu
	   
	        int choice;
	        do {
	            System.out.println("\n1. Deposit");
	            System.out.println("2. Withdraw");
	            System.out.println("3. Show Balance");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter deposit amount: ");
	                    double depositAmount = scanner.nextDouble();
	                    account.deposit(depositAmount);
	                    break;

	                case 2:
	                    System.out.print("Enter withdrawal amount: ");
	                    double withdrawAmount = scanner.nextDouble();
	                    account.withdraw(withdrawAmount);
	                    break;

	                case 3:
	                    account.showBalance();
	                    break;

	                case 4:
	                    System.out.println("Exiting...");
	                    break;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 4);

	        scanner.close();
	    }
	}


