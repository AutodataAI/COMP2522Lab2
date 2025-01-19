

/**
 * Class describing a Dragon. Class extends the Creature
 * Class because a dragon is always a Creature. Contains
 * a constructor, getDetails method and breatheFire
 * and restoreFirePower methods to both deal damage to
 * target creatures and to alter the amount of firepower
 * the Dragon currently has.
 *
 * @author Liam Pickrell
 * @version 1.0
 */
public class Dragon extends Creature
{

    private static final int HEALTH_MIN = 0;
    private static final int FIRE_POWER_MIN = 0;
    private static final int LOW_FIRE_POWER = 10;
    private static final int FIRE_POWER_DECREMENT = 10;
    private static final int DAMAGE_DECREMENT = 20;
    private static final int MIN_FIRE_POWER_AMOUNT = 0;
    private static final int MAX_FIRE_POWER_AMOUNT = 100;

    private int firePower;

    /**
     * Dragon Constructor.
     *
     * @param name name of the dragon.
     * @param dateOfBirth Dragon's date of birth.
     * @param health Dragon's health level.
     * @param firePower Dragon's firepower level.
     */
    public Dragon(String name,
                  Date dateOfBirth,
                  int health,
                  int firePower)
    {
        validateHealth();
        validateFirePower();

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
        this.firePower = firePower;
    }

    /*
    Validation method for health. Ensures
    health isn't below zero.
    @throw IllegalArgumentException if health is below zero.
     */
    private final void validateHealth()
    {
        if(this.health <= HEALTH_MIN)
        {
            throw new IllegalArgumentException("Invalid " +
                    "health: " + health);
        }
    }

    /*
    Validation method for firepower. Ensures firepower
     isn't below 0 and isn't above 100.
    @throw IllegalArgumentException if firepower is
    out of required range.
     */
    private final void validateFirePower()
    {
        if(this.firePower <= FIRE_POWER_MIN ||
                this.firePower >= MAX_FIRE_POWER_AMOUNT)
        {
            throw new IllegalArgumentException("Invalid " +
                    "fire power: " + firePower);
        }
    }

    /**
     * Concatenates a string of all the details of the Dragon.
     * Overrides the getDetails method for creature and
     * adds details about the firepower level.
     *
     * @param dragon the dragon to return details about.
     * @return string concatenation of details.
     */
    @Override
    public final String getDetails(Dragon dragon)
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Dragon Details: ")
                .append("\n\tName: ")
                .append(this.name)
                .append("\n\tDate of Birth: ")
                .append(this.dateOfBirth)
                .append("\n\tAge: ")
                .append(this.age)
                .append("\n\tHealth: ")
                .append(this.health)
                .append("\n\tFire Power: ")
                .append(this.firePower);

        return sb.toString();
    }

    /**
     * Method for the dragon to breathe fire. Argument is
     * the creature to breathe the fire onto. Reduces
     * firepower by 10 each time method is called, and
     * deals 20 damage to the target creature.
     *
     * @param target the creature that is targeted
     * @throws LowFirePowerException if firepower is below 10.
     */
    public final void beatheFire(Creature target)
            throws LowFirePowerException {
        if (this.firePower < LOW_FIRE_POWER) {
            throw new LowFirePowerException("Fire Power " +
                    "is too low to breathe fire: " + firePower);
        }
        this.firePower -= FIRE_POWER_DECREMENT;
        target.takeDamage(DAMAGE_DECREMENT);
    }

    /**
     * Method to restore the firepower levels. Checks first if
     * Fire Power restoration amount is between 0 and 100, and
     * the max total firepower of the dragon is 100 if the amount
     * brings it over 100.
     *
     * @param amount the amount of firepower to restore.
     * @throws IllegalArgumentException if amount to restore
     * isn't between 0 and 100.
     */
    public final void restoreFirePower(int amount)
    {
        if(amount <= MIN_FIRE_POWER_AMOUNT ||
                amount >= MAX_FIRE_POWER_AMOUNT)
        {
            throw new IllegalArgumentException("Fire Power " +
                    "Restoration amount needs to be " +
                    "between 0 and 100: " + amount);
        }

        this.firePower += amount;

        if (this.firePower > MAX_FIRE_POWER_AMOUNT)
        {
            this.firePower = MAX_FIRE_POWER_AMOUNT;
        }
    }

}
