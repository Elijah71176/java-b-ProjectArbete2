package se.elijah.adventure.model;

public class Burglar extends Entity {

    public Burglar() {
        super("Burglar", 12, 4);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: " + getRole() + " - an intruder with hostile intentions.");
    }

    public boolean isDefeated() {
        return !isConscious();
    }
}