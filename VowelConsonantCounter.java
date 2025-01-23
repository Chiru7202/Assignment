package com.training;

import java.util.Scanner;

public class VowelConsonantCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input string
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        // Converting string to lowercase 
        input = input.toLowerCase();

        
        int vowelCount = 0;
        int consonantCount = 0;

        // Iterate through the characters of the string
        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') { // Check if the character is a letter
                switch (c) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                        vowelCount++;
                        break;
                    default:
                        consonantCount++;
                        break;
                }
            }
        }

        System.out.println("Number of vowels: " + vowelCount);
        System.out.println("Number of consonants: " + consonantCount);

        scanner.close();
    }
}

