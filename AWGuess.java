// Programmer: Alexander Wolf
// CS 145
// 1/10/2022
// Purpose: This program plays a guessing game in which a user is prompted to
// guess a random number within a certain range until they guess correctly. The
// program provides hints to the user upon an incorrect guess and reports various
// statistics about the games to the user at the end of the program.

import java.util.Scanner; // import Scanner class
import java.util.Random; // import Random class

// begin AWGuess class
public class AWGuess {
    // begin main method
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in); // instantiate Scanner
        final int range = 100; // set limit of guessing range
        int gameCount = 0; // total number of games
        int numGuess; // initialize variable for guesses from individual game
        int totalGuess = 0; // guesses from all games
        int min = 9999; // fewest guesses in one game

        String intro = getIntro(range); // get introduction String
        System.out.println(intro); // print introduction

        // play game until user terminates
        char playAgain = 'Y'; // prime while loop
        while (playAgain == 'Y' || playAgain == 'y') {
            numGuess = playGame(range); // play one game and store number of guesses
            totalGuess += numGuess; // cumulative sum of guesses throughout games
            gameCount++; // cumulative sum of games

            if (numGuess < min) {
                min = numGuess; // reset fewest guesses in one game
            }

            // prompt user for valid playAgain command
            do {
                System.out.print("Do you want to play again? ");
                playAgain = console.next().charAt(0);
            } while (playAgain != 'Y' && playAgain != 'y' && playAgain != 'N'
                    && playAgain != 'n'); // end do while loop
            System.out.println();
        } // end game while loop

        // process game data and store String with results
        String results = getResults(gameCount, totalGuess, min);
        System.out.println(results); // print game data
    } // end main

    // begin getIntro method - return String which introduces game to user
    public static String getIntro(int range) {
        String intro = "This program allows you to play a guessing game.\n"
                + "I will think of a number between 1 and\n"
                + range + " and will allow you to guess until\n"
                + "you get it. For each guess, I will tell you\n"
                + "whether the right answer is higher or lower\n"
                + "than your guess.\n";

        return intro;
    } // end giveIntro

    // begin playGame method - play one game with the user
    public static int playGame(int range) {
        Random rand = new Random(); // instantiate Random object
        int number = rand.nextInt(range) + 1; // 1-100

        System.out.printf("I'm thinking of a number between 1 and %d...%n", range);
        int guess = getGuess(range); // get user's initial guess and store
        int numGuess = 1; // begin guess counter at 1

        // repeat prompt for guesses until correct number is guessed
        while (guess != number) {
            if (guess < number) {
                System.out.println("It's higher."); // hint to user
            } else {
                System.out.println("It's lower."); // hint to user
            }
            numGuess++; // increment guess counter
            guess = getGuess(range); // get a new guess from user
        } // when correct number guessed, end while loop

        if (numGuess == 1) {
            // case where user guesses correct number on first try
            System.out.println("You got it right in 1 guess.");
        } else {
            System.out.printf("You got it right in %d guesses.%n", numGuess);
        }
        return numGuess;
    } // end playGame

    // begin getResults method - pass in game data and return String of results
    public static String getResults(int gameCount, int totalGuess, int min) {
        // number of guesses per game rounded to one decimal place
        double guessPerGame = Math.round((double) totalGuess / (double) gameCount * 100) / 100;

        String results = "Overall results:\n"
                + "\ttotal games = " + gameCount + "\n"
                + "\ttotal guesses = " + totalGuess + "\n"
                + "\tguesses/game = " + guessPerGame + "\n"
                + "\tbest game = " + min;

        return results;
    } // end getResults

    // begin getGuess method - prompt user until a number in proper range is entered
    // post: number is between 1 and range
    public static int getGuess(int range) {
        int guess = getInt("Your guess? ");

        while (guess < 1 || guess > range) {
            System.out.println("Out of range; try again.");
            guess = getInt("Your guess? ");
        }
        return guess;
    } // end getGuess

    // begin getInt method - prompt user until an integer is entered
    // post: number is an integer
    public static int getInt(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        while (!console.hasNextInt()) {
            console.next(); // discard non-int input
            System.out.println("Not an integer; try again.");
            System.out.print(prompt);
        }
        return console.nextInt();
    } // end getInt
} // end AWGuess
