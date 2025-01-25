package com.training;

import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Array size must be greater than 0.");
            return;
        }

        // Initialize the array
        int[] array = new int[size];

        // Input array elements
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Input rotation direction
        System.out.println("Enter rotation direction (left or right):");
        String direction = scanner.next().toLowerCase();

        // Input number of rotations
        System.out.println("Enter the number of rotations:");
        int rotations = scanner.nextInt();

        if (rotations < 0) {
            System.out.println("Number of rotations must be non-negative.");
            return;
        }

        // Normalize rotations to prevent redundant rotations
        rotations %= size;

        // Perform rotation based on direction
        if (direction.equals("right")) {
            rotateRight(array, rotations);
        } else if (direction.equals("left")) {
            rotateLeft(array, rotations);
        } else {
            System.out.println("Invalid direction. Please enter 'left' or 'right'.");
            return;
        }

        // Output the rotated array
        System.out.println("Rotated array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    // Rotate array to the right
    private static void rotateRight(int[] array, int rotations) {
        int size = array.length;
        reverse(array, 0, size - 1);
        reverse(array, 0, rotations - 1);
        reverse(array, rotations, size - 1);
    }

    // Rotate array to the left
    private static void rotateLeft(int[] array, int rotations) {
        int size = array.length;
        reverse(array, 0, rotations - 1);
        reverse(array, rotations, size - 1);
        reverse(array, 0, size - 1);
    }

    // Reverse a portion of the array
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

