package com.training;

import java.io.*;
import java.util.*;

class Product implements Serializable {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Price: $" + price;
    }
}

class Customer implements Serializable {
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

class Order implements Serializable {
    private String orderId;
    private Customer customer;
    private List<Product> products;
    private double totalCost;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId).append("\n")
                .append("Customer: ").append(customer.getName()).append("\n")
                .append("Products: \n");

        for (Product product : products) {
            orderDetails.append("  - ").append(product).append("\n");
        }

        orderDetails.append("Total Cost: $").append(totalCost);
        return orderDetails.toString();
    }
}

class ECommerceSystem {
    private static final String ORDER_HISTORY_FILE = "order_history.dat";

    private Map<String, Product> products;
    private Map<String, Customer> customers;
    private List<Order> orders;

    public ECommerceSystem() {
        products = new HashMap<>();
        customers = new HashMap<>();
        orders = loadOrderHistory();
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product added successfully!");
    }

    public void removeProduct(String productId) {
        if (products.remove(productId) != null) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        System.out.println("Customer added successfully!");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n--- Product List ---");
            for (Product product : products.values()) {
                System.out.println(product);
            }
        }
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("\n--- Customer List ---");
            for (Customer customer : customers.values()) {
                System.out.println(customer);
            }
        }
    }

    public void createOrder(String orderId, String customerId, List<String> productIds) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        Order order = new Order(orderId, customer);
        for (String productId : productIds) {
            Product product = products.get(productId);
            if (product != null) {
                order.addProduct(product);
            } else {
                System.out.println("Product ID " + productId + " not found!");
            }
        }

        orders.add(order);
        System.out.println("Order created successfully!");
        saveOrderHistory();
    }

    public void displayOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No order history available.");
        } else {
            System.out.println("\n--- Order History ---");
            for (Order order : orders) {
                System.out.println(order);
                System.out.println("-----------------------");
            }
        }
    }

    private List<Order> loadOrderHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDER_HISTORY_FILE))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveOrderHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_HISTORY_FILE))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }
}

public class ECommerceApplication {
    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- E-Commerce Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Add Customer");
            System.out.println("4. Display Products");
            System.out.println("5. Display Customers");
            System.out.println("6. Create Order");
            System.out.println("7. Display Order History");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    system.addProduct(new Product(productId, name, price));
                }
                case 2 -> {
                    System.out.print("Enter Product ID to remove: ");
                    String productId = scanner.nextLine();
                    system.removeProduct(productId);
                }
                case 3 -> {
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    system.addCustomer(new Customer(customerId, name));
                }
                case 4 -> system.displayProducts();
                case 5 -> system.displayCustomers();
                case 6 -> {
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Product IDs (comma-separated): ");
                    String[] productIds = scanner.nextLine().split(",");
                    system.createOrder(orderId, customerId, Arrays.asList(productIds));
                }
                case 7 -> system.displayOrderHistory();
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

