package com.training;

import java.util.*;

abstract class Employee {
    private String id;
    private String name;
    private String department;
    private double baseSalary;

    public Employee(String id, String name, String department, double baseSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Base Salary: " + baseSalary;
    }
}

class PermanentEmployee extends Employee {
    private double bonus;

    public PermanentEmployee(String id, String name, String department, double baseSalary, double bonus) {
        super(id, name, department, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }
}

class ContractualEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public ContractualEmployee(String id, String name, String department, int hoursWorked, double hourlyRate) {
        super(id, name, department, 0); // Base salary is 0 for contractual employees
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (Employee employee : employees) {
                System.out.println(employee);
                System.out.println("Calculated Salary: " + employee.calculateSalary());
            }
        }
    }

    public void generateDepartmentReport(String department) {
        System.out.println("\n--- Department Report: " + department + " ---");
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getDepartment().equalsIgnoreCase(department)) {
                System.out.println(employee);
                System.out.println("Calculated Salary: " + employee.calculateSalary());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employees found in this department.");
        }
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Payroll payroll = new Payroll();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Payroll System ---");
            System.out.println("1. Add Permanent Employee");
            System.out.println("2. Add Contractual Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Generate Department Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Base Salary: ");
                    double baseSalary = scanner.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = scanner.nextDouble();
                    payroll.addEmployee(new PermanentEmployee(id, name, department, baseSalary, bonus));
                }
                case 2 -> {
                    System.out.print("Enter Employee ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();
                    payroll.addEmployee(new ContractualEmployee(id, name, department, hoursWorked, hourlyRate));
                }
                case 3 -> payroll.displayEmployees();
                case 4 -> {
                    System.out.print("Enter Department Name: ");
                    String department = scanner.nextLine();
                    payroll.generateDepartmentReport(department);
                }
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

