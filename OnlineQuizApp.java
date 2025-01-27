package com.training;

import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(questionText).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((i + 1)).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        System.out.println("Question added successfully!");
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void displayQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
        } else {
            System.out.println("\n--- Quiz Questions ---");
            for (int i = 0; i < questions.size(); i++) {
                System.out.println((i + 1) + ". " + questions.get(i).getQuestionText());
            }
        }
    }
}

class User {
    private String username;
    private int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }
}

class QuizSystem {
    private Quiz quiz;
    private Map<String, User> userScores;

    public QuizSystem() {
        quiz = new Quiz();
        userScores = new HashMap<>();
    }

    public void addQuestion(String questionText, List<String> options, int correctOption) {
        Question question = new Question(questionText, options, correctOption);
        quiz.addQuestion(question);
    }

    public void takeQuiz(String username) {
        if (quiz.getQuestions().isEmpty()) {
            System.out.println("No questions available for the quiz.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        User user = userScores.computeIfAbsent(username, User::new);

        System.out.println("\n--- Starting Quiz for " + username + " ---");
        for (Question question : quiz.getQuestions()) {
            System.out.println(question);
            System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
            int answer = scanner.nextInt();
            if (answer == question.getCorrectOption()) {
                user.incrementScore();
            }
        }

        System.out.println("\nQuiz Completed! " + username + ", your score is: " + user.getScore());
    }

    public void displayScores() {
        if (userScores.isEmpty()) {
            System.out.println("No users have taken the quiz yet.");
        } else {
            System.out.println("\n--- User Scores ---");
            for (Map.Entry<String, User> entry : userScores.entrySet()) {
                System.out.println("User: " + entry.getKey() + ", Score: " + entry.getValue().getScore());
            }
        }
    }
}

public class OnlineQuizApp {
    public static void main(String[] args) {
        QuizSystem quizSystem = new QuizSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Online Quiz System ---");
            System.out.println("1. Add Question");
            System.out.println("2. Take Quiz");
            System.out.println("3. Display Scores");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Question Text: ");
                    String questionText = scanner.nextLine();
                    System.out.println("Enter Options (separated by commas): ");
                    String[] optionsArray = scanner.nextLine().split(",");
                    List<String> options = Arrays.asList(optionsArray);
                    System.out.print("Enter Correct Option Number (1-" + options.size() + "): ");
                    int correctOption = scanner.nextInt();
                    quizSystem.addQuestion(questionText, options, correctOption);
                }
                case 2 -> {
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    quizSystem.takeQuiz(username);
                }
                case 3 -> quizSystem.displayScores();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

