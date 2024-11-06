package se.elijah.adventure.model;

import java.util.Scanner;

public class Resident extends Entity {
    private Scanner scanner = new Scanner(System.in);

    public Resident() {
        super("Resident", 12, 3);
    }

    public boolean fight(Burglar burglar) {
        System.out.println("A fight has started between the Resident and the Burglar!");

        while (this.isConscious() && burglar.isConscious()) {
            System.out.println("Do you want to continue fighting? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("no")) {
                System.out.println("You chose to stop fighting and escape.");
                return false; // Player chose to retreat
            }

            // Resident attacks Burglar
            burglar.takeDamage(this.getDamage());
            System.out.println("Resident attacks the Burglar and deals " + this.getDamage() + " damage.");
            System.out.println("Burglar's remaining health: " + burglar.getHealth());

            if (!burglar.isConscious()) {
                System.out.println("The Resident has defeated the Burglar!");
                return true;
            }

            // Burglar attacks Resident
            this.takeDamage(burglar.getDamage());
            System.out.println("Burglar attacks the Resident and deals " + burglar.getDamage() + " damage.");
            System.out.println("Resident's remaining health: " + this.getHealth());

            if (!this.isConscious()) {
                System.out.println("The Resident has been defeated by the Burglar.");
                return false;
            }
        }
        return this.isConscious();
    }

    @Override
    public void displayRole() {
        System.out.println("Role: " + getRole() + " - defender of the home.");
    }
}