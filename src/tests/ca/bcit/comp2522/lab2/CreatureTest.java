package ca.bcit.comp2522.lab2;

/**
 * Used to test the Creature, Elf, Dragon, and Orc classes,
 * and explore inheritance, polymorphism, and exceptions.
 *
 * @author Ted Ip
 * @author Liam Pickrell
 * @author Ryan Chu
 * @version 1.0
 */
class CreatureTest
{
    /**
     * Drives the tests.
     *
     * @param args unused
     */
    public static void main(final String[] args)
    {
        final Creature exOrc;
        final Creature exDragon;
        final Creature exElf;

        exOrc    = new Orc("exOrc",
                           new Date(2025,
                                    1,
                                    1),
                           50,
                           0);
        exDragon = new Dragon("exDragon",
                              new Date(2024,
                                       1,
                                       1),
                              50,
                              0);
        exElf    = new Elf("exElf",
                           new Date(2020,
                                    1,
                                    1),
                           50,
                           0);

        exOrc.getDetails();
        System.out.println("exOrc.getClass(): " + exOrc.getClass());
        System.out.println("exOrc intanceof Creature: " + (exOrc instanceof Creature));
        System.out.println("exOrc intanceof Orc: " + (exOrc instanceof Orc));
        System.out.println("exOrc intanceof Elf: " + (exOrc instanceof Elf));
        System.out.println("exOrc intanceof Dragon: " + (exOrc instanceof Dragon) + "\n");

        exDragon.getDetails();
        System.out.println("exDragon.getClass(): " + exDragon.getClass());
        System.out.println("exDragon intanceof Creature: " + (exDragon instanceof Creature));
        System.out.println("exDragon intanceof Orc: " + (exDragon instanceof Orc));
        System.out.println("exDragon intanceof Elf: " + (exDragon instanceof Elf));
        System.out.println("exDragon intanceof Dragon: " + (exDragon instanceof Dragon) + "\n");

        exElf.getDetails();
        System.out.println("exElf.getClass(): " + exElf.getClass());
        System.out.println("exElf intanceof Creature: " + (exElf instanceof Creature));
        System.out.println("exElf intanceof Orc: " + (exElf instanceof Orc));
        System.out.println("exElf intanceof Elf: " + (exElf instanceof Elf));
        System.out.println("exElf intanceof Dragon: " + (exElf instanceof Dragon) + "\n");

        Orc    realOrc    = (Orc) exOrc;
        Dragon realDragon = (Dragon) exDragon;
        Elf    realElf    = (Elf) exElf;


        try
        {
            System.out.println("\nTesting DamageException");
            System.out.println("realElf.takeDamage(-100)");
            realElf.takeDamage(-100);
        } catch (final Exception damageEx) {
            System.out.println("Caught: " + damageEx.toString());
        }

        try
        {
            System.out.println("\nTesting HealingException");
            System.out.println("realElf.heal(-100)");
            realElf.heal(-100);
        } catch (final Exception healingEx) {
            System.out.println("Caught: " + healingEx.toString());
        }

        try
        {
            System.out.println("\nTesting LowManaException");
            System.out.println("realElf mana: " + realElf.getMana());
            System.out.println("realElf.castSpell()");
            realElf.castSpell();
        } catch (final Exception manaEx) {
            System.out.println("Caught: " + manaEx.toString());
        }

        try
        {
            System.out.println("\nTesting LowFirePowerException");
            System.out.println("realDragon firepower: " + realDragon.getFirePower());
            System.out.println("realDragon.breatheFire()");
            realDragon.breatheFire();
        } catch (final Exception fireEx) {
            System.out.println("Caught: " + fireEx.toString());
        }

        System.out.println("\nRestoring firepower and mana");
        System.out.println("realDragon.restoreFirePower(1000);\n" + "realElf.restoreMana(500);");
        realDragon.restoreFirePower(1000);
        realElf.restoreMana(1000);
        System.out.println("realDragon firepower: " + realDragon.getFirePower() + "\nrealElf mana:" + realElf.getMana());

        try
        {
            System.out.println("\nrealElf.takeDamage(realDragon.breatheFire())");
            realElf.takeDamage(realDragon.breatheFire());
        } catch (final Exception fireEx) {
            System.out.println("Caught: " + fireEx.toString());
        }

        try
        {
            System.out.println("realOrc.takeDamage(realElf.castSpell())");
            realOrc.takeDamage(realElf.castSpell());
        } catch (final Exception manaEx) {
            System.out.println("Caught: " + manaEx.toString());
        }

        System.out.println("realDragon.takeDamage(realOrc.berserk())");
        realDragon.takeDamage(realOrc.berserk());

        realOrc.getDetails();
        realDragon.getDetails();
        realElf.getDetails();

    }
}
