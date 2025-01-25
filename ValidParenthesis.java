package com.training;

import java.util.*;

public class ValidParenthesis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the string
        System.out.println("Enter a string containing only {}, [], and ():");
        String input = scanner.nextLine();

        // Check if the string is valid
        boolean isValid = isValidParentheses(input);

        // Output the result
        if (isValid) {
            System.out.println("The string is valid.");
        } else {
            System.out.println("The string is invalid.");
        }
    }

    private static boolean isValidParentheses(String s) {
        // Stack to track opening brackets
        Stack<Character> stack = new Stack<>();

        // Iterate through the characters in the string
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Handle closing brackets
            else if (ch == ')' || ch == '}' || ch == ']') {
                // Check if the stack is empty or the top of the stack doesn't match
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            } else {
                // Invalid character found
                return false;
            }
        }

        // If the stack is empty, all brackets are matched
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}

