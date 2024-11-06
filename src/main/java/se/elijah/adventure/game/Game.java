package se.elijah.adventure.game;

import se.elijah.adventure.model.Burglar;
import se.elijah.adventure.model.Resident;

import java.util.Scanner;

public class Game {
    // Constants representing rooms
    public static final String BEDROOM = "BEDROOM";
    public static final String OFFICE = "OFFICE";
    public static final String VISITOR_ROOM = "VISITOR_ROOM";
    public static final String SITTING_ROOM = "SITTING_ROOM";
    public static final String KITCHEN = "KITCHEN";
    public static final String HALL = "HALL";

    // Game state variables
    private String currentRoom;
    private boolean running;
    private boolean fryingPanFound = false;
    private boolean burglarKnockedOut = false;

    // Game objects
    private Scanner scanner;
    private Resident resident;
    private Burglar burglar;

    public Game() {
        this.currentRoom = SITTING_ROOM;
        this.running = true;
        this.resident = new Resident();
        this.burglar = new Burglar();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        showWelcomeMessage();
        while (running) {
            System.out.println("You're in the " + currentRoom);
            if (!navigateRooms()) {
                break;
            }
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Welcome to the combat game.");
        System.out.println("Use commands BEDROOM, OFFICE, VISITOR_ROOM, SITTING_ROOM, KITCHEN, HALL to navigate, or 'QUIT' to exit.");
    }

    private boolean navigateRooms() {
        System.out.println("Where do you want to go?");
        String direction = getUserInput().toUpperCase();

        switch (direction) {
            case "BEDROOM" -> navigateToRoom(BEDROOM,  this::exploreBedroom);
            case "OFFICE" -> navigateToRoom(OFFICE, this::enterOffice);
            case "VISITOR_ROOM" -> navigateToRoom(VISITOR_ROOM, this::exploreVisitorRoom);
            case "SITTING_ROOM" -> navigateToRoom(SITTING_ROOM, this::exploreSittingRoom);
            case "KITCHEN" -> navigateToRoom(KITCHEN, this::exploreKitchen);
            case "HALL" -> navigateToRoom(HALL, this::confrontBurglar);  // Confront burglar in the hall
            case "QUIT" -> exitGame();
            default -> System.out.println("Invalid command.");
        }
        return running;
    }
// 2 target d player want and  will be executed when the player
// successfully navigates to the target room.
    private void navigateToRoom(String room, Runnable roomAction) {
        if (currentRoom.equals(SITTING_ROOM) || room.equals(SITTING_ROOM)) {
            if (!currentRoom.equals(room)) {
                roomAction.run();
                currentRoom = room;
            } else {
                System.out.println("You're already in this room.");
            }
        } else {
            System.out.println("You must return to the sitting room before going to " + room + ".");
        }
    }

    private String getUserInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private void exitGame() {
        System.out.println("You chose to exit. Goodbye!");
        running = false;
    }

    private void exploreBedroom() {
        System.out.println("Only a big bed and wardrobe.");
    }

    private void enterOffice() {
        if (burglar.isDefeated()) {
            System.out.println("You enter the office. The stress subsides, and you instinctively pick up the phone to call the police.");
            System.out.println("The police arrive quickly. You are safe. Game Over - You Win!");
            running = false;
        } else {
            System.out.println("You enter the office, but you’re too stressed to think about calling the police.");
        }
    }

    private void exploreVisitorRoom() {
        System.out.println("You're in the visitor's room; only a bed and wardrobe are here.");
    }

    private void exploreSittingRoom() {
        System.out.println("This is the centre of the house, where you can navigate to other rooms.");
        System.out.println("The sitting room has a chair, TV, wall clock, etc.");
    }

    private void exploreKitchen() {
        if (!fryingPanFound) {
            System.out.println("You're in the kitchen. You see a frying pan on the counter.");
            System.out.println("You pick up the frying pan, feeling ready to defend yourself!");
            resident.addDamage(3);  // Increase damage for the Resident
            fryingPanFound = true;
        } else {
            System.out.println("You're in the kitchen, but there's nothing of interest here anymore.");
        }
    }

    private void confrontBurglar() {
        if (!burglarKnockedOut) {
            System.out.println("You encounter a burglar in the hall! Prepare for battle.");
            burglar.displayRole();

            // Start the fight between the Resident and the Burglar
            boolean victory = resident.fight(burglar);

            if (victory) {
                burglarKnockedOut = true;
                System.out.println("You knocked out the burglar!");
            } else {
                System.out.println("You lost the battle. Game over.");
                running = false;
            }
        } else {
            System.out.println("The hall is calm now; the burglar is defeated.");
        }
    }
}