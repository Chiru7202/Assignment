package com.training;

import java.util.Scanner;

public class PallindrokmeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input string
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        //  remove spaces and convert to lowercase
        String processedString = input.toLowerCase().replaceAll("\\s", "");

        // Check if the string is a palindrome
        if (isPalindrome(processedString)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }

        scanner.close();
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        // Compare characters from both ends
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}



