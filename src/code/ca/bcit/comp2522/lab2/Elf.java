package ca.bcit.comp2522.lab2;

/**
 * Class describing an Elf. Class extends the Creature
 * Class because an elf is always a Creature. Contains
 * a constructor, getDetails method and castSpell
 * and restoreMana methods to both deal damage to
 * target creatures and to alter the amount of mana
 * the Elf currently has.
 *
 * @author Liam Pickrell
 * @author Ryan Chu
 * @author Ted Ip
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int MANA_MIN = 0;
    private static final int MANA_MAX = 50;
    private static final int DAMAGE_DECREMENT = 10;
    private static final int MANA_DECREMENT = 5;
    private static final int LOW_MANA = 5;
    private static final int MIN_MANA_AMOUNT = 0;
    private static final int MAX_MANA_AMOUNT = 50;

    public int mana;

    public Elf(String name,
               Date dateOfBirth,
               int health,
               int mana)
    {
        super(name, dateOfBirth, health);
        validateMana(mana);
        this.mana = mana;
    }


    /**
     * Validation method for Mana. Ensures firepower
     * isn't below 0 and isn't above 50.
     * out of required range.
     */
    private void validateMana(final int mana)
    {
        if(mana <= MANA_MIN ||
                mana >= MANA_MAX)
        {
            throw new IllegalArgumentException("Invalid " +
                    "mana: " + mana);
        }
    }

    /**
     * Getter for mana.
     *
     * @return mana as an int.
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * Setter for Mana. First validates Mana before
     * setting the new value.
     *
     * @param mana of the Elf as an int.
     */
    public void setMana(int mana)
    {
        validateMana(mana);
        this.mana = mana;
    }

    /**
     * Concatenates a string of all the details of the Elf.
     * Overrides the getDetails method for creature and
     * adds details about the mana level.
     */
    @Override
    public final void getDetails()
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Elf Details: ")
                .append("\n\tName: ")
                .append(this.getName())
                .append("\n\tDate of Birth: ")
                .append(this.getDateOfBirth().getYyyyMmDd())
                .append("\n\tAge: ")
                .append(this.getAgeYears())
                .append("\n\tHealth: ")
                .append(this.getHealth())
                .append("\n\tMana: ")
                .append(this.mana);

        System.out.println(sb.toString());
    }

    /**
     * Method for the Elf to cast a spell. Argument is
     * the creature to cast the spell onto. Reduces
     * mana by 5 each time method is called, and
     * deals 10 damage to the target creature.
     *
     * @param target the creature that is targeted
     * @throws LowManaException if mana is below 5.
     */
    public final void castSpell(Creature target)
            throws LowManaException
    {
        if (mana < LOW_MANA) {
            throw new LowManaException("Mana " +
                    "is too low to cast spell: " + mana);
        }
        mana -= MANA_DECREMENT;
        target.takeDamage(DAMAGE_DECREMENT);
    }

    /**
     * Method to restore the mana levels. Checks first if
     * Mana restoration amount is between 0 and 50, and
     * the max total mana of the Elf is 50 if the amount
     * brings it over 50.
     *
     * @param amount the amount of mana to restore.
     * @throws IllegalArgumentException if amount to restore
     * isn't between 0 and 50.
     */
    public final void restoreMana(int amount)
    {
        if(amount <= MIN_MANA_AMOUNT ||
                amount >= MAX_MANA_AMOUNT)
        {
            throw new IllegalArgumentException("Mana " +
                    "Restoration amount needs to be " +
                    "between 0 and 50: " + amount);
        }

        mana += amount;

        if (mana > MAX_MANA_AMOUNT)
        {
            mana = MAX_MANA_AMOUNT;
        }
    }

}
