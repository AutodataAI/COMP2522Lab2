package ca.bcit.comp2522.lab2;

/**
 * Represents an Orc, which is a Creature with the unique instance
 * variable rage, and method beserk which deals increase rage by 5.
 * If rage is above 20, the orc deal 20 more damage
 *
 * @author Liam Pickrell
 * @author Ryan Chu
 * @author Ted Ip
 * @version 1.0
 */
class Orc
        extends Creature
{
    private static final int MAX_RAGE = 30;
    private static final int MIN_RAGE = 0;

    private static final int MIN_RAGE_FOR_ERROR = 5;
    private static final int RAGE_INCREMENT     = 5;
    private static final int MIN_RAGE_FOR_MULTIPLIER   = 21;

    private static final int DAMAGE_DECREMENT = 15;
    private static final int BERSERK_DAMAGE_MULTIPLIER = 2;

    private int rage;

    /**
     * Constructs an orc.
     * Uses Creature constructor and it's validations.
     * Rage must be a value between MIN_RAGE and MAX_RAGE
     *
     * @param name        the name of the Orc
     * @param dateOFBirth the date of birth of the Orc
     * @param health      the initial health of the Orc
     * @param rage        the initial rage level of the Orc
     */
    Orc(final String name,
        final Date dateOFBirth,
        int health,
        int rage)
    {
        super(name,
              dateOFBirth,
              health);

        validateRage(rage);

        this.rage = rage;
    }

    /*
     * Validation method for rage.
     * Ensures rage isn't below MIN_RAGE and isn't above MAX_RAGE.
     *
     * @param rage the rage to check
     */
    private void validateRage(final int rage)
    {
        if(rage < MIN_RAGE || rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("Invalid rage: " +
                                               rage +
                                               ". Expected between " +
                                               MIN_RAGE +
                                               " and " +
                                               MAX_RAGE);
        }
    }

    /**
     * Getter for rage.
     *
     * @return rage as an int.
     */
    public int getRage()
    {
        return rage;
    }

    /**
     * Setter for rage.
     * First validates rage is between MIN_RAGE and MAX_RAGE
     * before setting the new value.
     *
     * @param rage the rage to set as an int.
     */
    public void setRage(final int rage)
    {
        validateRage(rage);
        this.rage = rage;
    }

    /**
     * Prints a summary of the Orc to the console,
     * Including the Orc's unique rage value.
     * Details include the name, date of birth, age, health, and rage.
     */
    @Override
    public void getDetails()
    {
        StringBuilder details;

        details = new StringBuilder();

        details.append("Orc Details:")
               .append("\n\tName: ")
               .append(this.getName())
               .append("\n\tDate of Birth: ")
               .append(this.getDateOfBirth()
                           .getYyyyMmDd())
               .append("\n\tAge: ")
               .append(this.getAgeYears())
               .append("\n\tHealth: ")
               .append(this.getHealth())
               .append("\n\tRage: ")
               .append(this.getRage());

        System.out.println(details.toString());
    }

    /**
     * Attacks which deals damage and increases rage
     *
     * <p>
     * Damage done is equal to DAMAGE_DECREMENT.
     * Rage is increased by RAGE_INCREMENT
     * Throws an error if rage is below MIN_RAGE_FOR_ERROR
     * </p>
     *
     * @return the damage dealt
     */
    public int berserk()
    {
        setRage(getRage() + RAGE_INCREMENT);

        if (getRage() <= MIN_RAGE_FOR_ERROR)
        {
            throw new LowRageException("Rage value is below " + MIN_RAGE_FOR_ERROR);
        }

        final int damageDone;

        if (getRage() > MIN_RAGE_FOR_MULTIPLIER)
        {
            damageDone = DAMAGE_DECREMENT * BERSERK_DAMAGE_MULTIPLIER;
        }
        else
        {
            damageDone = DAMAGE_DECREMENT;
        }

        return  damageDone;

    }
}
