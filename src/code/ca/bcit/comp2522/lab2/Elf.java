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
class Elf
        extends Creature
{
    private static final int MIN_MANA = 0;
    private static final int MAX_MANA = 50;

    private static final int DAMAGE_DECREMENT = 10;
    private static final int MANA_DECREMENT   = 5;

    public int mana;

    /**
     * Elf Constructor.
     * Has the same restrictions of the creature constructor with the addition mana,
     * which must be between MIN_MANA and MAX_MANA
     *
     * @param name        name of the elf.
     * @param dateOfBirth Elf's date of birth.
     * @param health      Elf's health level.
     * @param mana        Elf's mana.
     */
    Elf(String name,
        Date dateOfBirth,
        int health,
        int mana)
    {
        super(name,
              dateOfBirth,
              health);

        validateMana(mana);

        this.mana = mana;
    }

    /*
     * Validation method for mana.
     * Ensures mana isn't below MIN_MANA and isn't above MAX_MANA.
     *
     * @param mana the mana to check
     */
    private void validateMana(final int mana)
    {
        if(mana <= MIN_MANA || mana >= MAX_MANA)
        {
            throw new IllegalArgumentException("Invalid mana: " +
                                               mana +
                                               ". Expected between " +
                                               MIN_MANA +
                                               " and " +
                                               MAX_MANA);
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
     * Setter for mana.
     * First validates mana is between MIN_MANA and MAX_MANA
     * before setting the new value.
     *
     * @param mana the mana to set as an int.
     */
    public void setMana(final int mana)
    {
        validateMana(mana);
        this.mana = mana;
    }

    /**
     * Prints a summary of the Elf to the console,
     * Including the Elf's unique mana value.
     * Details include the name, date of birth, age, health, and mana.
     */
    @Override
    public void getDetails()
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Elf Details: ")
          .append("\n\tName: ")
          .append(this.getName())
          .append("\n\tDate of Birth: ")
          .append(this.getDateOfBirth()
                      .getYyyyMmDd())
          .append("\n\tAge: ")
          .append(this.getAgeYears())
          .append("\n\tHealth: ")
          .append(this.getHealth())
          .append("\n\tMana: ")
          .append(this.mana);

        System.out.println(sb.toString());
    }

    /**
     * Casts a spell which deals damage and reduces mana.
     *
     * <p>
     * Damage done is equal to DAMAGE_DECREMENT.
     * Mana is decreased by MANA_DECREMENT
     * Throws an error if mana is below MANA_DECREMENT
     * </p>
     *
     * @return the damage done
     * @throws LowManaException if mana is below MANA_DECREMENT.
     */
    public int castSpell() throws
                           LowManaException
    {
        if(mana < MANA_DECREMENT)
        {
            throw new LowManaException("Low mana: " +
                                       mana +
                                       ". Must be above: " +
                                       MANA_DECREMENT);
        }

        mana -= MANA_DECREMENT;
        return DAMAGE_DECREMENT;
    }

    /**
     * Method to restore the mana levels.
     * <p>
     * Mana restoration amount must be above MIN_MANA.
     * Increases mana without exceeding MAX_MANA
     * </p>
     *
     * @param amount the amount of mana to restore.
     * @throws IllegalArgumentException if amount
     *                                  isn't above MIN_MANA.
     */
    public void restoreMana(int amount)
    {
        if(amount < MIN_MANA)
        {
            throw new IllegalArgumentException("Invalid mana to restore: " +
                                               amount +
                                               ". must be equal to or above: " +
                                               MIN_MANA);
        }

        mana += amount;

        if(mana > MAX_MANA)
        {
            mana = MAX_MANA;
        }
    }

}
