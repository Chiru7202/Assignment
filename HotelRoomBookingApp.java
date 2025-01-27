package com.training;

import java.util.*;

class Room {
    private String roomId;
    private String type;
    private boolean isAvailable;
    private double pricePerNight;

    public Room(String roomId, String type, double pricePerNight) {
        this.roomId = roomId;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Type: " + type + ", Price/Night: $" + pricePerNight + ", Available: " + isAvailable;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomId) {
        super(roomId, "Standard", 100.0);
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomId) {
        super(roomId, "Deluxe", 200.0);
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name;
    }
}

class Booking {
    private Room room;
    private Customer customer;
    private int nights;

    public Booking(Room room, Customer customer, int nights) {
        this.room = room;
        this.customer = customer;
        this.nights = nights;
    }

    public double calculateTotalCost() {
        return room.getPricePerNight() * nights;
    }

    @Override
    public String toString() {
        return "Booking Details: \n" +
                "Customer: " + customer.getName() + " (" + customer.getCustomerId() + ")\n" +
                "Room: " + room.getRoomId() + " (" + room.getType() + ")\n" +
                "Nights: " + nights + ", Total Cost: $" + calculateTotalCost();
    }
}

class HotelBookingSystem {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings;

    public HotelBookingSystem() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    // Room Operations
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room added successfully!");
    }

    public void displayRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // Customer Operations
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    public void displayCustomers() {
        System.out.println("\n--- Customers ---");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // Booking Operations
    public void bookRoom(String roomId, String customerId, int nights) {
        Room room = findRoomById(roomId);
        Customer customer = findCustomerById(customerId);

        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available for booking.");
            return;
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Booking booking = new Booking(room, customer, nights);
        bookings.add(booking);
        room.setAvailable(false);
        System.out.println("Room booked successfully! Total Cost: $" + booking.calculateTotalCost());
    }

    public void checkOutRoom(String roomId) {
        Room room = findRoomById(roomId);

        if (room != null && !room.isAvailable()) {
            room.setAvailable(true);
            System.out.println("Room checked out successfully!");
        } else {
            System.out.println("Room is already available or not found.");
        }
    }

    public void displayBookings() {
        System.out.println("\n--- Bookings ---");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private Room findRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}

public class HotelRoomBookingApp {
    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Room Booking System ---");
            System.out.println("1. Add Room");
            System.out.println("2. Add Customer");
            System.out.println("3. Display Rooms");
            System.out.println("4. Display Customers");
            System.out.println("5. Book Room");
            System.out.println("6. Check-Out Room");
            System.out.println("7. Display Bookings");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Room Type (standard/deluxe): ");
                    String type = scanner.nextLine().toLowerCase();
                    System.out.print("Enter Room ID: ");
                    String roomId = scanner.nextLine();

                    if (type.equals("standard")) {
                        system.addRoom(new StandardRoom(roomId));
                    } else if (type.equals("deluxe")) {
                        system.addRoom(new DeluxeRoom(roomId));
                    } else {
                        System.out.println("Invalid room type.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    system.addCustomer(new Customer(customerId, name));
                }
                case 3 -> system.displayRooms();
                case 4 -> system.displayCustomers();
                case 5 -> {
                    System.out.print("Enter Room ID to book: ");
                    String roomId = scanner.nextLine();
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Number of Nights: ");
                    int nights = scanner.nextInt();
                    system.bookRoom(roomId, customerId, nights);
                }
                case 6 -> {
                    System.out.print("Enter Room ID to check-out: ");
                    String roomId = scanner.nextLine();
                    system.checkOutRoom(roomId);
                }
                case 7 -> system.displayBookings();
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

