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
class Dragon
        extends Creature
{
    private static final int MIN_FIRE_POWER = 0;
    private static final int MAX_FIRE_POWER = 100;

    private static final int DAMAGE_DECREMENT     = 20;
    private static final int FIRE_POWER_DECREMENT = 10;

    private int firePower;

    /**
     * Dragon Constructor.
     * Has the same restrictions of the creature constructor with the addition firepower,
     * which must be between MIN_FIRE_POWER and MAX_FIRE_POWER
     *
     * @param name        name of the dragon.
     * @param dateOfBirth Dragon's date of birth.
     * @param health      Dragon's health level.
     * @param firePower   Dragon's firepower.
     */
    Dragon(String name,
           Date dateOfBirth,
           int health,
           int firePower)
    {
        super(name,
              dateOfBirth,
              health);

        validateFirePower(firePower);

        this.firePower = firePower;
    }

    /*
     * Validation method for firepower. Ensures firepower
     * isn't below MIN_FIRE_POWER and isn't above MAX_FIRE_POWER.
     *
     * @param firePower the firePower to check
     */
    private void validateFirePower(final int firePower)
    {
        if(firePower <= MIN_FIRE_POWER || firePower >= MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Invalid fire power: " +
                                               firePower +
                                               ". Expected between " +
                                               MIN_FIRE_POWER +
                                               " and " +
                                               MAX_FIRE_POWER);
        }
    }

    /**
     * Getter for firepower
     *
     * @return firepower as an int.
     */
    public int getFirePower()
    {
        return firePower;
    }

    /**
     * Setter for firepower.
     * First validates firepower is between MIN_FIRE_POWER and MAX_FIRE_POWER
     * before setting it.
     *
     * @param firePower the firepower to set as an int.
     */
    public void setFirePower(int firePower)
    {
        validateFirePower(firePower);
        this.firePower = firePower;
    }

    /**
     * Prints a summary of the Dragon to the console,
     * Including the Dragon's unique firepower value.
     * Details include the name, date of birth, age, health, and firepower.
     */
    @Override
    public void getDetails()
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Dragon Details: ")
          .append("\n\tName: ")
          .append(this.getName())
          .append("\n\tDate of Birth: ")
          .append(this.getDateOfBirth()
                      .getYyyyMmDd())
          .append("\n\tAge: ")
          .append(this.getAgeYears())
          .append("\n\tHealth: ")
          .append(this.getHealth())
          .append("\n\tFire Power: ")
          .append(this.firePower);

        System.out.println(sb.toString());
    }

    /**
     * Breathes fire which deals damage and reduces firepower.
     *
     * <p>
     * Damage done is equal to DAMAGE_DECREMENT.
     * Firepower is decreased by FIRE_POWER_DECREMENT
     * Throws an error if firepower is below FIRE_POWER_DECREMENT
     * </p>
     *
     * @return the damage done
     * @throws LowFirePowerException if mana is below FIRE_POWER_DECREMENT.
     */
    public int beatheFire() throws
                            LowFirePowerException
    {
        if(firePower < FIRE_POWER_DECREMENT)
        {
            throw new LowFirePowerException("Low firepower: " +
                                       firePower +
                                       ". Must be above: " +
                                       FIRE_POWER_DECREMENT);
        }

        firePower -= FIRE_POWER_DECREMENT;
        return DAMAGE_DECREMENT;
    }

    /**
     * Method to restore the firepower levels.
     * <p>
     * Firepower restoration amount must be above MINE_FIRE_POWER.
     * Increases firepower without exceeding MAX_FIRE_POWER
     * </p>
     *
     * @param amount the amount of firepower to restore.
     * @throws IllegalArgumentException if amount
     *                                  isn't above MIN_FIRE_POWER.
     */
    public final void restoreFirePower(int amount)
    {
        if(amount < MIN_FIRE_POWER)
        {
            throw new IllegalArgumentException("Invalid firepower to restore: " + amount + ". must be equal to or above: " + MIN_FIRE_POWER);
        }

        firePower += amount;

        if(firePower > MAX_FIRE_POWER)
        {
            firePower = MAX_FIRE_POWER;
        }
    }

}
