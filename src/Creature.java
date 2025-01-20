
/**
 * Class of creature. Contains methods of constructor, 
 * to check if creature is alive,
 * adjust health after damage is taken, 
 * adjust health after healing is received
 * calculate creature's age
 * print creature's details.
 * @author Ted IP
 * @version 1.0
 * */

public class Creature {

    private static final int DEAD_HEALTH_LEVEL = 0;
    private static final int CURRENT_YEAR = 2025;
    private static final int CURRENT_MONTH = 1;
    private static final int CURRENT_DAY = 19;

    private final String name;
    private final Date dateOfBirth;
    private int health;

    /**
     * Constructor of Creature.
     * 
     * @param String name name of creature
     * @param Date   date of birth of creature
     * @param health starting health of creature
     * @throws IllegalArgumentException if parameter is invalid
     */
    public Creature(final String name, final Date dateOfBirth, // validity has been checked in Date constructor already
            final int health) {
        if (name == null || name == "" || health > 100 || health < 1) {
            throw new IllegalArgumentException("Inputted paramters are invalid.");
        }

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;

    }

    /**
     * Check if alive.
     * 
     * @return boolean true when alive
     */
    public boolean isAlive() {
        return health > DEAD_HEALTH_LEVEL;
    }

    /**
     * Unchecked Damage Exception
     */
    class DamageException extends RuntimeException {
        DamageException(String message) {
            super(message);
        }
    }

    /**
     * Adjust creature's health when it takes damage.
     * 
     * @param damage taken
     */
    public void takeDamage(final int damage) {

        if (damage < 0) {
            throw new DamageException("Damage cannot be negative.");
        }

        health = health - damage;
        if (health < 0) {
            health = 0;
        }
    }

    /**
     * Unchecked healing Exception
     */
    class HealingException extends RuntimeException {
        HealingException(String message) {
            super(message);
        }
    }

    /**
     * Adjust creature's health when it is healed.
     * 
     * @param heal received
     */
    public void heal(final int healAmount) {

        if (healAmount < 0) {
            throw new HealingException("Healing cannot be negative.");
        }

        health = health + healAmount;
        if (health > 100) {
            health = 100;
        }
    }

    /**
     * Calculate creature's age in years based on date of birth.
     * @return int cretaure's age in year
     */
    public int getAgeYears() {
        int result;
        result = CURRENT_YEAR - dateOfBirth.getYear() - 1;
        if (CURRENT_MONTH > dateOfBirth.getMonth()) {
            return result + 1;
        }
        if (CURRENT_MONTH == dateOfBirth.getMonth()) {
            if (CURRENT_DAY >= dateOfBirth.getDay()) {
                return result + 1;
            }
        }
        return result;
    }
    
    /**
     * Print details of creature.*/
    public void getDetails() {
        System.out.println(name + ", " + 
                            dateOfBirth.getYyyyMmDd() + ", " + 
                            getAgeYears() + ", " + 
                            health);
    }
}