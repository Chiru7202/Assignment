package com.training;

import java.util.*;

class Student {
    private String id;
    private String name;
    private HashMap<String, Double> grades; // Course ID -> Grade

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addGrade(String courseId, double grade) {
        grades.put(courseId, grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;

        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ":");
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            System.out.println("Course: " + entry.getKey() + " | Grade: " + entry.getValue());
        }
    }
}

class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}

public class StudentManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static HashMap<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Display Student Information");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    calculateGPA();
                    break;
                case 5:
                    displayStudentInfo();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private static void addCourse() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        courses.put(courseId, new Course(courseId, courseName));
        System.out.println("Course added successfully!");
    }

    private static void assignGrade() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        if (!courses.containsKey(courseId)) {
            System.out.println("Course not found!");
            return;
        }

        System.out.print("Enter Grade: ");
        double grade = scanner.nextDouble();
        student.addGrade(courseId, grade);
        System.out.println("Grade assigned successfully!");
    }

    private static void calculateGPA() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println("GPA of " + student.getName() + ": " + student.calculateGPA());
    }

    private static void displayStudentInfo() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println("\nStudent Name: " + student.getName());
        student.displayGrades();
        System.out.println("GPA: " + student.calculateGPA());
    }

    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}

