package com.training;

import java.util.Scanner;

	public class AnagramChecker {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Input 
	        System.out.println("Enter the first string: ");
	        String str1 = scanner.nextLine().toLowerCase().replaceAll("\\s", ""); // Convert to lowercase 
	        System.out.println("Enter the second string: ");
	        String str2 = scanner.nextLine().toLowerCase().replaceAll("\\s", ""); 

	        // Check if the strings are anagrams
	        if (areAnagrams(str1, str2)) {
	            System.out.println("The strings are anagrams.");
	        } else {
	            System.out.println("The strings are not anagrams.");
	        }

	        scanner.close();
	    }

	    public static boolean areAnagrams(String str1, String str2) {
	        // If lengths are not equal, they cannot be anagrams
	        if (str1.length() != str2.length()) {
	            return false;
	        }

	        // Create an array to count character occurrences for 'a' to 'z'
	        int[] charCount = new int[26];

	        // Count characters in the first string
	        for (char c : str1.toCharArray()) {
	            if (c >= 'a' && c <= 'z') {
	                charCount[c - 'a']++;
	            }
	        }

	        // Subtract character counts based on the second string
	        for (char c : str2.toCharArray()) {
	            if (c >= 'a' && c <= 'z') {
	                charCount[c - 'a']--;
	            }
	        }

	        // Check if all counts are zero
	        for (int count : charCount) {
	            if (count != 0) {
	                return false;
	            }
	        }

	        return true;
	    }
	}


