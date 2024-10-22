package com.rishi.Task1;


import java.util.Scanner;

public class NumberGame {
    int random = (int)Math.ceil(Math.random() * 100.0);
    int maxAttempts = 5;

    NumberGame() {
    }

    int guess(int num) {
        if (num == this.random) {
            return 0;
        } else {
            return num > this.random ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int round = 0;
        int score = 0;

        String playAgain;
        do {
            NumberGame game = new NumberGame();
            ++round;
            System.out.println("Round " + round + ": Guess the number between 1 and 100. You have " + game.maxAttempts + " attempts.");
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            while(attempts < game.maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Guess the number: ");
                int guessNum = input.nextInt();
                int result = game.guess(guessNum);
                ++attempts;
                int var10001;
                if (result == 1) {
                    System.out.println("Guess a lower number.");
                    var10001 = game.maxAttempts - attempts;
                    System.out.println("You have lefts " + var10001);
                } else if (result == -1) {
                    System.out.println("Guess a higher number.");
                    var10001 = game.maxAttempts - attempts;
                    System.out.println("You have lefts " + var10001);
                } else {
                    System.out.println("You guessed the number correctly in " + attempts + " attempts!");
                    ++score;
                    hasGuessedCorrectly = true;
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You've used all " + game.maxAttempts + " attempts. The correct number was " + game.random);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = input.next();
        } while(playAgain.equalsIgnoreCase("yes"));

        System.out.println("Game Over! You won " + score + " round(s).");
        input.close();
    }
}

