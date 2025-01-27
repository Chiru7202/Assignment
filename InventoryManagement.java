package com.training;

import java.util.*;

interface SupplierOperations {
    void addSupplier(String supplierId, String name, String contact);
    void displaySuppliers();
}

class Product {
    private String productId;
    private String name;
    private int stock;
    private double price;

    public Product(String productId, String name, int stock, double price) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Stock: " + stock + ", Price: $" + price;
    }
}

class Supplier {
    private String supplierId;
    private String name;
    private String contact;

    public Supplier(String supplierId, String name, String contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Supplier ID: " + supplierId + ", Name: " + name + ", Contact: " + contact;
    }
}

class InventoryManagementSystem implements SupplierOperations {
    private Map<String, Product> products;
    private Map<String, Supplier> suppliers;

    public InventoryManagementSystem() {
        products = new HashMap<>();
        suppliers = new HashMap<>();
    }

    // Product Operations
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product added successfully!");
    }

    public void updateProductStock(String productId, int newStock) {
        Product product = products.get(productId);
        if (product != null) {
            product.setStock(newStock);
            System.out.println("Product stock updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    public void removeProduct(String productId) {
        if (products.remove(productId) != null) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product not found!");
        }
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

    public void displayLowStockProducts(int threshold) {
        boolean found = false;
        System.out.println("\n--- Low Stock Products ---");
        for (Product product : products.values()) {
            if (product.getStock() < threshold) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products are below the stock threshold.");
        }
    }

    // Supplier Operations
    @Override
    public void addSupplier(String supplierId, String name, String contact) {
        suppliers.put(supplierId, new Supplier(supplierId, name, contact));
        System.out.println("Supplier added successfully!");
    }

    @Override
    public void displaySuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers available.");
        } else {
            System.out.println("\n--- Supplier List ---");
            for (Supplier supplier : suppliers.values()) {
                System.out.println(supplier);
            }
        }
    }
}

public class InventoryManagementApp {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Stock");
            System.out.println("3. Remove Product");
            System.out.println("4. Display Products");
            System.out.println("5. Display Low Stock Products");
            System.out.println("6. Add Supplier");
            System.out.println("7. Display Suppliers");
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
                    System.out.print("Enter Product Stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    ims.addProduct(new Product(productId, name, stock, price));
                }
                case 2 -> {
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter New Stock: ");
                    int newStock = scanner.nextInt();
                    ims.updateProductStock(productId, newStock);
                }
                case 3 -> {
                    System.out.print("Enter Product ID to remove: ");
                    String productId = scanner.nextLine();
                    ims.removeProduct(productId);
                }
                case 4 -> ims.displayProducts();
                case 5 -> {
                    System.out.print("Enter Stock Threshold: ");
                    int threshold = scanner.nextInt();
                    ims.displayLowStockProducts(threshold);
                }
                case 6 -> {
                    System.out.print("Enter Supplier ID: ");
                    String supplierId = scanner.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Supplier Contact: ");
                    String contact = scanner.nextLine();
                    ims.addSupplier(supplierId, name, contact);
                }
                case 7 -> ims.displaySuppliers();
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

