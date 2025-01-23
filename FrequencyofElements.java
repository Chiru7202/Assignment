package com.training;

import java.util.Scanner;

    public class FrequencyofElements {
       
    	public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Input the size of the array
	        System.out.println("Enter the size of the array: ");
	        int size = scanner.nextInt();

	        // Input the elements of the array
	        int[] array = new int[size];
	        System.out.println("Enter the elements of the array: ");
	        for (int i = 0; i < size; i++) {
	            array[i] = scanner.nextInt();
	        }

	        // Array to track visited elements
	        boolean[] visited = new boolean[size];

	        // Finding the frequency of each element
	        System.out.println("Element frequencies:");
	        for (int i = 0; i < size; i++) {
	            if (!visited[i]) { // Checking if the element is already counted
	                int count = 1;
	                for (int j = i + 1; j < size; j++) {
	                    if (array[i] == array[j]) {
	                        count++;
	                        visited[j] = true; // Mark as visited
	                    }
	                }
	                // Print Element and frequency
	                System.out.println(array[i] + ": " + count);
	            }
	        }

	        scanner.close();
	    }
	}


