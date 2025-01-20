package ca.bcit.comp2522.lab2;

public class CreatureTest
{
    public static void main(final String[] args)
    {
        Creature exOrc;
        Creature exDragon;
        Creature exElf;
        exOrc = new Orc("exOrc", new Date(2024, 1, 1), 50, 5);
        exDragon = new Dragon("exDragon", new Date(2024, 1, 1), 50, 5);
        exElf = new Elf("exElf", new Date(2024, 1, 1), 50, 5);
        exOrc.getDetails();
        exDragon.getDetails();
        exElf.getDetails();

    }
}
