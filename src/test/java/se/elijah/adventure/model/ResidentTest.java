package se.elijah.adventure.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IsConsciousTest {
    private Resident resident;
    private Burglar burglar;

    @BeforeEach
    void setUp() {
        resident = new Resident();
        burglar = new Burglar();
    }

    @Test
    void testIsConscious_whenHealthAboveZero_shouldReturnTrue() {
        assertTrue(resident.isConscious(), "Resident should be conscious initially.");
        assertTrue(burglar.isConscious(), "Burglar should be conscious initially.");
    }

    @Test
    void testIsConscious_whenHealthIsZero_shouldReturnFalse() {
        resident.takeDamage(resident.getHealth());  // Depletes health to zero
        assertFalse(resident.isConscious(), "Resident should not be conscious after health is zero.");

        burglar.takeDamage(burglar.getHealth());  // Depletes health to zero
        assertFalse(burglar.isConscious(), "Burglar should not be conscious after health is zero.");
    }
}