
/**
 * Class describing an Elf. Class extends the Creature
 * Class because an elf is always a Creature. Contains
 * a constructor, getDetails method and castSpell
 * and restoreMana methods to both deal damage to
 * target creatures and to alter the amount of mana
 * the Elf currently has.
 *
 * @author Liam Pickrell
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int HEALTH_MIN = 0;
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
        validateHealth();
        validateMana();

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
        this.mana = mana;
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
    Validation method for Mana. Ensures firepower
     isn't below 0 and isn't above 50.
    @throw IllegalArgumentException if Mana is
    out of required range.
     */
    private final void validateMana()
    {
        if(this.mana <= MANA_MIN ||
                this.mana >= MANA_MAX)
        {
            throw new IllegalArgumentException("Invalid " +
                    "mana: " + mana);
        }
    }

    /**
     * Concatenates a string of all the details of the Elf.
     * Overrides the getDetails method for creature and
     * adds details about the mana level.
     *
     * @param elf the dragon to return details about.
     * @return string concatenation of details.
     */
    @Override
    public final String getDetails(Elf elf)
    {
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Elf Details: ")
                .append("\n\tName: ")
                .append(this.name)
                .append("\n\tDate of Birth: ")
                .append(this.dateOfBirth)
                .append("\n\tAge: ")
                .append(this.age)
                .append("\n\tHealth: ")
                .append(this.health)
                .append("\n\tMana: ")
                .append(this.mana);

        return sb.toString();
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
        if (this.mana < LOW_MANA) {
            throw new LowManaException("Mana " +
                    "is too low to cast spell: " + mana);
        }
        this.mana -= MANA_DECREMENT;
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

        this.mana += amount;

        if (this.mana > MAX_MANA_AMOUNT)
        {
            this.mana = MAX_MANA_AMOUNT;
        }
    }

}
