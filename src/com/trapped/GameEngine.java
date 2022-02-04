package com.trapped;

import com.trapped.player.Player;
import com.trapped.utilities.FileManager;
import com.trapped.utilities.Prompts;
import com.trapped.utilities.Sounds;
import com.trapped.utilities.TextColor;

import java.io.Serializable;

import static com.trapped.utilities.TextColor.*;

public class GameEngine implements Serializable {
    private boolean quitGame = false;


    public static void startGame() {
        while (true) {
            FileManager.getResource("./splash_screen.txt");   // displaying splash screen

            //System.out.println(TextColor.RED + "\nPlease select an option from the menu." + TextColor.RESET);
            System.out.println("\nPlease select an option from the menu.");

            int userInput = Prompts.getIntInput();

            switch (userInput) {
                case 1:
                    FileManager.readMessageSlowly("greeting.txt", 0);
                    System.out.println("\n--------------------------------");
                    System.out.println("What is your name: ");
                    String name = scanner.next();
                    System.out.println("\n\nHello, " + BLUE_BOLD + name.toUpperCase() + RESET);

                    FileManager.readMessageSlowly("introstory.txt", 0);
                    Sounds.playSounds("phone.wav", 4000);

                    FileManager.readMessageSlowly("intro_after_phone.txt", 0);

                    playGame();
                case 2: // quit game option
                    System.out.println("Exiting the game. Thank you for playing");
                    System.exit(0);

                default:
                    System.out.println("Invalid input! Please enter a number corresponding to one of the menu options.");
            }
        }
    }

    public static void playGame() {
        while (true) {
            Player.viewRoom();
        }
    }


}



