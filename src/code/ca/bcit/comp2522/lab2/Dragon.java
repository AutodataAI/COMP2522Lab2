package ca.bcit.comp2522.lab2;

/**
 * Class describing a Dragon. Class extends the Creature
 * Class because a dragon is always a Creature. Contains
 * a constructor, getDetails method and breatheFire
 * and restoreFirePower methods to both deal damage to
 * target creatures and to alter the amount of firepower
 * the Dragon currently has.
 *
 * @author Liam Pickrell
 * @author Ryan Chu
 * @author Ted Ip
 * @version 1.0
 */
public class Dragon extends Creature
{
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
        super(name, dateOfBirth, health);

        validateFirePower(firePower);


        this.firePower = firePower;
    }

    /**
     * Getter for Fire Power
     *
     * @return the Fire Power of the Dragon as an int.
     */
    public int getFirePower()
    {
        return firePower;
    }

    /**
     * Setter for Fire Power. First validates Fire Power
     * first before setting it.
     *
     * @param firePower as an int.
     */
    public void setFirePower(int firePower)
    {
        validateFirePower(firePower);
        this.firePower = firePower;
    }

    /**
     * Validation method for firepower. Ensures firepower
     * isn't below 0 and isn't above 100.
     */
    private void validateFirePower(final int firePower)
    {
        if(firePower <= FIRE_POWER_MIN ||
                firePower >= MAX_FIRE_POWER_AMOUNT)
        {
            throw new IllegalArgumentException("Invalid " +
                    "fire power: " + firePower);
        }
    }

    /**
     * Concatenates a string of all the details of the Dragon.
     * Overrides the getDetails method for creature and
     * adds details about the firepower level.
     */
    @Override
    public final void getDetails()
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Dragon Details: ")
                .append("\n\tName: ")
                .append(this.getName())
                .append("\n\tDate of Birth: ")
                .append(this.getDateOfBirth().getYyyyMmDd())
                .append("\n\tAge: ")
                .append(this.getAgeYears())
                .append("\n\tHealth: ")
                .append(this.getHealth())
                .append("\n\tFire Power: ")
                .append(this.firePower);

        System.out.println(sb.toString());
    }

    /*
     * TODO: ask jason if this function should take a target
     *  and call takeDamage in the function or return
     * an int value to be used in the main function
     * such as exElf.takeDamage(exDragon.breathFire())
     */
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
        if (firePower < LOW_FIRE_POWER) {
            throw new LowFirePowerException("Fire Power " +
                    "is too low to breathe fire: " + firePower);
        }
        firePower -= FIRE_POWER_DECREMENT;
        target.takeDamage(DAMAGE_DECREMENT);
    }

    //TODO: isn't this supposed add to the given firepower not set it?
    //TODO: ask jason if firepower going above 100 is supposed to throw an error or just set it back to 100
    /**
     * Method to restore the firepower levels. Checks first if
     * Fire Power restoration amount is between 0 and 100, and
     * the max total firepower of the dragon is 100 if the amount
     * brings it over 100.
     *
     * @param amount the amount of firepower to restore.
     * isn't between 0 and 100.
     */
    public final void restoreFirePower(int amount)
    {
        if(amount <= MIN_FIRE_POWER_AMOUNT ||
           amount >= MAX_FIRE_POWER_AMOUNT)
        {
            throw new IllegalArgumentException("Fire Power " +
                    "Restoration amount needs to be " +
                    "between " + MIN_FIRE_POWER_AMOUNT +
                    "and " + MAX_FIRE_POWER_AMOUNT + "." +
                    amount);
        }

        firePower += amount;

        if (firePower > MAX_FIRE_POWER_AMOUNT)
        {
            firePower = MAX_FIRE_POWER_AMOUNT;
        }
    }

}
