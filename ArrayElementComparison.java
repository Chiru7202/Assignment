package com.training;

import java.util.Scanner;

public class ArrayElementComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the arrays
        System.out.println("Enter the size of the arrays: ");
        int size = scanner.nextInt();

        // Declare the arrays
        int[] array1 = new int[size];
        int[] array2 = new int[size];

        // Input elements for the array
        System.out.println("Enter elements of the first array:");
        for (int i = 0; i < size; i++) {
            array1[i] = scanner.nextInt();
        }

     
        System.out.println("Enter elements of the second array:");
        for (int i = 0; i < size; i++) {
            array2[i] = scanner.nextInt();
        }

        // Compare elements of both arrays
        System.out.println("Comparison results:");
        for (int i = 0; i < size; i++) {
            if (array1[i] == array2[i]) {
                System.out.println("Element " + (i + 1) + ": Equal");
            } else if (array1[i] > array2[i]) {
                System.out.println("Element " + (i + 1) + ": Greater");
            } else {
                System.out.println("Element " + (i + 1) + ": Lesser");
            }
        }

        scanner.close();
    }
}

