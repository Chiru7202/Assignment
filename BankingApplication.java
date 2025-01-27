package com.training;

import java.util.*;

abstract class Account {
    private String accountNumber;
    private String customerName;
    private double balance;

    public Account(String accountNumber, String customerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    public abstract void displayAccountDetails();
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String customerName, double initialBalance, double interestRate) {
        super(accountNumber, customerName, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Savings Account: " + getAccountNumber() + 
                           ", Name: " + getCustomerName() + 
                           ", Balance: " + getBalance() + 
                           ", Interest Rate: " + interestRate + "%");
    }
}

class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
            double newBalance = getBalance() - amount;
            System.out.println("Withdrawal successful! New balance: " + newBalance);
            return true;
        } else {
            System.out.println("Insufficient balance or overdraft limit exceeded.");
            return false;
        }
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Current Account: " + getAccountNumber() + 
                           ", Name: " + getCustomerName() + 
                           ", Balance: " + getBalance() + 
                           ", Overdraft Limit: " + overdraftLimit);
    }
}

class Bank {
    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully!");
    }

    public Account findAccount(String accountNumber) {
        return accounts.stream().filter(acc -> acc.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("\n--- Account List ---");
            for (Account account : accounts) {
                account.displayAccountDetails();
            }
        }
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) {
        Account sender = findAccount(fromAccount);
        Account receiver = findAccount(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            System.out.println("Transfer successful!");
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Add Savings Account");
            System.out.println("2. Add Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    double interestRate = scanner.nextDouble();
                    bank.addAccount(new SavingsAccount(accNumber, name, balance, interestRate));
                }
                case 2 -> {
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Enter Overdraft Limit: ");
                    double overdraft = scanner.nextDouble();
                    bank.addAccount(new CurrentAccount(accNumber, name, balance, overdraft));
                }
                case 3 -> {
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.nextLine();
                    Account account = bank.findAccount(accNumber);
                    if (account == null) {
                        System.out.println("Account not found.");
                    } else {
                        System.out.print("Enter Deposit Amount: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.nextLine();
                    Account account = bank.findAccount(accNumber);
                    if (account == null) {
                        System.out.println("Account not found.");
                    } else {
                        System.out.print("Enter Withdrawal Amount: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Sender Account Number: ");
                    String fromAcc = scanner.nextLine();
                    System.out.print("Enter Receiver Account Number: ");
                    String toAcc = scanner.nextLine();
                    System.out.print("Enter Transfer Amount: ");
                    double amount = scanner.nextDouble();
                    bank.transferFunds(fromAcc, toAcc, amount);
                }
                case 6 -> bank.displayAllAccounts();
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

