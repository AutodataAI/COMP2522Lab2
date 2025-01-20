package ca.bcit.comp2522.lab2;

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
    private static final int MIN_ALIVE_HEALTH = 1;
    private static final int MAX_HEALTH = 100;
    private static final int SAME_DATE = 0;
    private static final Date CURRENT_DATE;

    //TODO: ask jason if a static initializer block should be used here
    static
    {
        CURRENT_DATE = new Date(2025, 1, 19);
    }


    private final String name;
    private final Date dateOfBirth;
    private int health;

    //TODO: ask jason if the parameter limits in javadoc should be in the param lines, the main text block, or both
    /**
     * Constructs a Creature using a name, date of birth, and health value.
     *
     * <p>
     * Given name must be not null nor blank
     * Given health value must be between MIN_ALIVE_HEALTH and MAX_HEALTH
     * </p>
     *
     * @param name the name of the creature (not null or blank)
     * @param dateOfBirth the birthdate of the creature
     * @param health the starting health of the creature (between MIN_ALIVE_HEALTH and MAX_HEALTH)
     */
    public Creature(final String name, final Date dateOfBirth, // validity has been checked in Date constructor already
            final int health) {

        validateName(name);
        validateHealth(health);
        validateDate();

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;

    }

    /**
     * Validates if the given name is not null and not blank,
     * if it is null/blank throws an exception.
     *
     * @param name the name to check
     */
    private void validateName(final String name) {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Given name is null or empty.");
        }
    }

    /**
     * Validates if the given health value is between
     * MIN_ALIVE_HEALTH and MAX_HEAlTH, if not between them
     * throws an exception.
     *
     * @param health the health value to check.
     */
    private void validateHealth(final int health)
    {
        if (health < MIN_ALIVE_HEALTH || health > MAX_HEALTH)
        {
            StringBuilder invalidHealthMsg;
            invalidHealthMsg = new StringBuilder();

            invalidHealthMsg.append("Invalid health given. Expected: " );
            invalidHealthMsg.append(MIN_ALIVE_HEALTH);
            invalidHealthMsg.append(" to ");
            invalidHealthMsg.append(MAX_HEALTH);
            invalidHealthMsg.append(". Got: ");
            invalidHealthMsg.append(health);
            throw new IllegalArgumentException(invalidHealthMsg.toString());
        }
    }

    /**
     * Validates if the given date of birth is earlier than CURRENT_DATE chronologically
     * throws an exception if it later.
     *
     * @param dateOfBirth the date of birth to check
     */
    private void validateDateOfBirth(final Date dateOfBirth)
    {
        if(CURRENT_DATE.compareTo(dateOfBirth) < SAME_DATE)
        {
            throw new IllegalArgumentException("Given date of birth (" +
                                               dateOfBirth.getYyyyMmDd() +
                                               ") is later than the current date: " +
                                               CURRENT_DATE.getYyyyMmDd());
        }
    }

    /**
     * Check if the creature is alive (health > DEAD_HEALTH_LEVEL)
     * 
     * @return boolean true if the creature is alive
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