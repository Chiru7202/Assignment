package com.training;

import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE = "members.dat";

    private List<Book> books;
    private List<Member> members;

    public LibraryManagementSystem() {
        books = loadData(BOOKS_FILE);
        members = loadData(MEMBERS_FILE);
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display Books");
            System.out.println("4. Display Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> library.addBook(scanner);
                case 2 -> library.addMember(scanner);
                case 3 -> library.displayBooks();
                case 4 -> library.displayMembers();
                case 5 -> library.issueBook(scanner);
                case 6 -> library.returnBook(scanner);
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    library.saveData();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private void addMember(Scanner scanner) {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        members.add(new Member(id, name));
        System.out.println("Member added successfully!");
    }

    private void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n--- Book List ---");
            books.forEach(System.out::println);
        }
    }

    private void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
        } else {
            System.out.println("\n--- Member List ---");
            members.forEach(System.out::println);
        }
    }

    private void issueBook(Scanner scanner) {
        System.out.print("Enter Book ID to issue: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        Book book = findBook(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued!");
            return;
        }

        if (findMember(memberId) == null) {
            System.out.println("Member not found!");
            return;
        }

        book.setIssued(true);
        System.out.println("Book issued successfully!");
    }

    private void returnBook(Scanner scanner) {
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();

        Book book = findBook(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("Book is not issued!");
            return;
        }

        book.setIssued(false);
        System.out.println("Book returned successfully!");
    }

    private Book findBook(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    private Member findMember(String id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    private void saveData() {
        saveToFile(BOOKS_FILE, books);
        saveToFile(MEMBERS_FILE, members);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private <T> void saveToFile(String fileName, List<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}

class Book implements Serializable {
    private final String id;
    private final String title;
    private final String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}

class Member implements Serializable {
    private final String id;
    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Member ID: " + id + ", Name: " + name;
    }
}

