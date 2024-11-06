package se.elijah.adventure.model;

public abstract class Entity {
    private String role;
    private int health;
    private int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void punch(Entity toPunch) {
        toPunch.takeDamage(this.damage);
    }

    public void addDamage(int additionalDamage) {
        this.damage += additionalDamage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isConscious() {
        return health > 0;
    }

    public abstract void displayRole();
}