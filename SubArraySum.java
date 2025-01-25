package com.training;

import java.util.Scanner;

public class SubArraySum {
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

        // Find the maximum sum of the subarray
        int maxSum = findMaxSubarraySum(array);

        // Output the result
        System.out.println("Maximum sum of a contiguous subarray is: " + maxSum);
    }

    private static int findMaxSubarraySum(int[] array) {
        int maxSum = array[0]; // Initialize with the first element
        int currentSum = array[0]; // Tracks the current subarray sum

        for (int i = 1; i < array.length; i++) {
            // Update the current sum using a conditional operator
            currentSum = Math.max(array[i], currentSum + array[i]);

            // Update the maximum sum dynamically
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}



