package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("res/dictionary/words_alpha.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());

        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        // Word Computer choose
        System.out.println(word);
        // Initiate and add player guess to a list
        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;

        while (true) {

            printHangMan(wrongCount);
            if(wrongCount >= 6) {
                System.out.println("You Lose!");
                System.out.println("The word was - "+ word);
                break;
            }

            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)) {
                break;
            }

//			If statement if you want to win by guessing the word.

			System.out.println("Please enter your guess for the word or hit enter to continue:");
			if (keyboard.nextLine().equals(word)) {
				System.out.println("Congradulations!");
				break;
			} else {
				System.out.println("Nope! Try again!");

			}
        }


    }



    public static void printHangMan(int wrongCount) {
        System.out.println("");
        System.out.println(" -------");
        System.out.println(" |     |");


        if (wrongCount >= 1) {

            System.out.println(" O     |");

        }

        if (wrongCount >= 2) {
            System.out.print("\\");

            if (wrongCount >= 3) {
                System.out.println("/     |");


            } else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |     |");


        }

        if (wrongCount >= 5) {
            System.out.print("/");


            if (wrongCount >= 6) {
                System.out.println("\\     |");


            } else {
                System.out.println("");
            }

        }

        System.out.println("       |");
        System.out.println("_______|_");
        System.out.println("");
        System.out.println("");
    }



    public static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        // Asks user for guess
        System.out.print("Your guess: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        System.out.println("");

        return word.contains(letterGuess);

    }

    /**
     * Prints out the current state of the hangman game.
     *
     * @param word
     * @param playerGuesses
     */
    public static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i) + " ");
                correctCount++;
                if(word.length() == correctCount)
                {
                    System.out.println();
                    System.out.println("Congradulations!");
                }
            } else {
                System.out.print("- ");
            }
        }
        System.out.println("");
        //System.out.println("(Lives left: X)"); We can chose to integrate this at another time.

        return (word.length() == correctCount);
    }

}
