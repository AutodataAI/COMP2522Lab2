package ca.bcit.comp2522.lab2;

/**
 * Represents an Orc, which is a Creature with the unique instance
 * variable rage, and method beserk which deals increase rage by 5.
 * If rage is above 20, the orc deal 20 more damage
 *
 * @author Ryan Chu
 * @version 1.0
 */
class Orc extends Creature
{
    private static final int BASE_DAMAGE = 15;

    private static final int MAX_RAGE = 30;
    private static final int MIN_RAGE = 0;

    private static final int BERSERK_RAGE_INCREASE     = 5;
    private static final int MIN_RAGE_FOR_MULTIPLIER   = 21;
    private static final int BERSERK_DAMAGE_MULTIPLIER = 2;

    private int rage;

    /**
     * Constructs an orc.
     * Uses Creature constructor and it's validations.
     * Rage must be a value between MIN_RAGE and MAX_RAGE
     *
     * @param name the name of the Orc
     * @param dateOFBirth the date of birth of the Orc
     * @param health the initial health of the Orc
     * @param rage the initial rage level of the Orc
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

    /**
     * Validates the given rage is between MIN_RAGE and MAX_RAGE,
     * throws and exception if not
     *
     * @param rage teh rage to check
     */
    private void validateRage(final int rage)
    {
        if(rage < MIN_RAGE || rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("rage must be between " + MIN_RAGE + " and " + MAX_RAGE);
        }
    }

    /**
     * Returns the rage level.
     *
     * @return the rage level as an int
     */
    public int getRage()
    {
        return rage;
    }

    /**
     * Sets the rage to the specified value between MIN_RAGE and MAX_RAGE
     * throws and exception if not
     *
     * @param rage the rage to set to
     */
    public void setRage(int rage)
    {
        validateRage(rage);
        this.rage = rage;
    }

    /**
     * Prints a summary of the Orc to the console, including the Orc's unique rage value
     */
    //TODO: ask jason if one should call the getter within the function or use the this. reference
    //TODO: ask jason if one should use the this reference at all points one needs to access the instance variables
    @Override
    public void getDetails()
    {
        StringBuilder details;

        details = new StringBuilder();

        details.append("Orc Details:")
            .append("\n\tName: ")
            .append(this.getName())
            .append("\n\tDate of Birth: ")
            .append(this.getDateOfBirth().getYyyyMmDd())
            .append("\n\tAge: ")
            .append(this.getAgeYears())
            .append("\n\tHealth: ")
            .append(this.getHealth())
            .append("\n\tRage: ")
            .append(this.getRage());

        System.out.println(details.toString());
    };

    //TODO: finish funtion when I get back info on how to handle damage
    /**
     * Deals BASE_DAMAGE damage.
     * Deals BASE_DAMAGE * BESERK_DAMAGE_MULTIPLIER if rage is above MIN_RAGE_FOR_MULTIPLIER
     * @return the damage dealt
     */
    //TODO:throw exception on rage < 5
    public int berserk()
    {
        setRage(getRage() + BERSERK_RAGE_INCREASE);

        return (getRage() > MIN_RAGE_FOR_MULTIPLIER) ? BASE_DAMAGE : BASE_DAMAGE * BERSERK_DAMAGE_MULTIPLIER;

    }
}
