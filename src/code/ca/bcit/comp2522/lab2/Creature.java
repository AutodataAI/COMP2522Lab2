package ca.bcit.comp2522.lab2;

/**
 * Class of creature. Contains methods of constructor,
 * to check if creature is alive,
 * adjust health after damage is taken,
 * adjust health after healing is received
 * calculate creature's age
 * print creature's details.
 *
 * @author Ted Ip
 * @author Liam Pickrell
 * @author Ryan Chu
 * @version 1.0
 */
class Creature
{

    private static final int DEAD_HEALTH_LEVEL = 0;
    private static final int MIN_ALIVE_HEALTH  = 1;
    private static final int MAX_HEALTH        = 100;

    private static final int NOTHING                        = 0;
    private static final int SAME_DATE                      = 0;
    private static final int BEFORE_BIRTHDAY_IN_YEAR_OFFSET = -1;

    private static final Date CURRENT_DATE;

    static
    {
        CURRENT_DATE = new Date(2025,
                                1,
                                23);
    }

    private final String name;
    private final Date   dateOfBirth;
    private       int    health;

    /**
     * Constructs a Creature using a name, date of birth, and health value.
     *
     * <p>
     * Given name must be not null nor blank
     * Given health value must be between MIN_ALIVE_HEALTH and MAX_HEALTH
     * </p>
     *
     * @param name        the name of the creature (not null or blank)
     * @param dateOfBirth the birthdate of the creature
     * @param health      the starting health of the creature
     *                    (between MIN_ALIVE_HEALTH and MAX_HEALTH)
     */
    Creature(final String name,
             final Date dateOfBirth,
             // validity has been checked in Date constructor already
             final int health)
    {

        validateName(name);
        validateHealth(health);
        validateDateOfBirth(dateOfBirth);

        this.name        = name;
        this.dateOfBirth = dateOfBirth;
        this.health      = health;

    }

    /*
     * Validates if the given name is not null and not blank,
     * if it is null/blank throws an exception.
     *
     * @param name the name to check
     */
    private void validateName(final String name)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Given " + "name is null or empty: " + name);
        }
    }

    /*
     * Validates if the given health value is between
     * MIN_ALIVE_HEALTH and MAX_HEAlTH, if not between them
     * throws an exception.
     *
     * @param health the health value to check.
     */
    private void validateHealth(final int health)
    {
        if(health < MIN_ALIVE_HEALTH || health > MAX_HEALTH)
        {
            StringBuilder invalidHealthMsg;
            invalidHealthMsg = new StringBuilder();

            invalidHealthMsg.append("Invalid health given. Expected: ");
            invalidHealthMsg.append(MIN_ALIVE_HEALTH);
            invalidHealthMsg.append(" to ");
            invalidHealthMsg.append(MAX_HEALTH);
            invalidHealthMsg.append(". Got: ");
            invalidHealthMsg.append(health);
            throw new IllegalArgumentException(invalidHealthMsg.toString());
        }
    }

    /*
     * Validates if the given date of birth is null or later than
     * CURRENT_DATE chronologically throws an exception if it is.
     *
     * @param dateOfBirth the date of birth to check
     */
    private void validateDateOfBirth(final Date dateOfBirth)
    {
        if(dateOfBirth == null || CURRENT_DATE.compareTo(dateOfBirth) < SAME_DATE)
        {
            throw new IllegalArgumentException("Given date of birth (" +
                                               dateOfBirth.getYyyyMmDd() +
                                               ") is later than the current date: " +
                                               CURRENT_DATE.getYyyyMmDd());
        }
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person as a String
     */
    protected String getName()
    {
        return name;
    }

    /**
     * Returns the date of birth of the person.
     *
     * @return the date of birth as a {@link Date} object
     */
    protected Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Returns the health status of the person.
     *
     * @return the health status as an integer value
     */
    protected int getHealth()
    {
        return health;
    }

    /**
     * Sets the health to the specified value.
     * Must be a value between MAX_HEALTH and MIN_ALIVE_HEALTH
     * or be equal to DEAD_HEALTH_LEVEL
     *
     * @param health the value to set health to
     */
    protected void setHealth(final int health)
    {
        if(health != DEAD_HEALTH_LEVEL)
        {
            validateHealth(health);
        }
        this.health = health;
    }

    /**
     * Check if the creature is alive (health > DEAD_HEALTH_LEVEL)
     *
     * @return boolean true if the creature is alive
     */
    protected boolean isAlive()
    {
        return health > DEAD_HEALTH_LEVEL;
    }

    /**
     * Adjust creature's health by the given damage value.
     *
     * @param damage the damage taken
     */
    protected void takeDamage(final int damage)
    {

        if(damage < NOTHING)
        {
            throw new DamageException("Damage cannot be negative.");
        }

        health = health - damage;
        if(health < DEAD_HEALTH_LEVEL)
        {
            health = DEAD_HEALTH_LEVEL;
        }
    }

    /**
     * Adjust creature's health when it is healed.
     *
     * @param healAmount the health received
     */
    protected void heal(final int healAmount)
    {

        if(healAmount < NOTHING)
        {
            throw new HealingException("Healing cannot be negative.");
        }

        health = health + healAmount;
        if(health > MAX_HEALTH)
        {
            health = MAX_HEALTH;
        }
    }

    /**
     * Calculate the creature's age in years based on date of birth.
     *
     * @return the creature's age in years as an int
     */
    protected int getAgeYears()
    {
        final int result;

        if(CURRENT_DATE.getMonth() < dateOfBirth.getMonth() ||
           (CURRENT_DATE.getMonth() == dateOfBirth.getMonth() && CURRENT_DATE.getDay() < dateOfBirth.getDay()))
        {
            result = CURRENT_DATE.getYear() - dateOfBirth.getYear() + BEFORE_BIRTHDAY_IN_YEAR_OFFSET;
        }
        else
        {
            result = CURRENT_DATE.getYear() - dateOfBirth.getYear();
        }
        return result;
    }

    /**
     * Prints details of the creature to the console.
     *
     * Details include the name, date of birth, age, and health
     */
    protected void getDetails()
    {
        StringBuilder details;

        details = new StringBuilder();

        details.append("Creature Details: ")
               .append("\n\tName: ")
               .append(this.getName())
               .append("\n\tDate of Birth: ")
               .append(this.getDateOfBirth()
                           .getYyyyMmDd())
               .append("\n\tAge: ")
               .append(this.getAgeYears())
               .append("\n\tHealth: ")
               .append(this.getHealth());

        System.out.println(details.toString());
    }
}