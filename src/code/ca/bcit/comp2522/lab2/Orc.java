package ca.bcit.comp2522.lab2;

class Orc extends Creature
{
    private static final int BASE_DAMAGE = 15;

    private static final int MAX_RAGE = 30;
    private static final int MIN_RAGE = 0;

    private static final int BERSERK_RAGE_INCREASE = 5;
    private static final int MIN_RAGE_FOR_BERSERK_MULTIPLIER = 21;
    private static final int BERSERK_DAMAGE_MULTIPLIER = 2;

    private int rage;

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

    private void validateRage(final int rage)
    {
        if(rage < MIN_RAGE || rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("rage must be between " + MIN_RAGE + " and " + MAX_RAGE);
        }
    }

    public int getRage()
    {
        return rage;
    }

    public void setRage(int rage)
    {
        this.rage = rage;
    }

    //TODO: ask jason if one should call the getter within the function or use the this. reference
    //TODO: ask jason if one should use the this reference at all points one needs to access the instance varriables
    @Override
    public void getDetails()
    {
        StringBuilder details;

        details = new StringBuilder();

        details.append("\n\tName: ")
            .append(this.getName())
            .append("\n\tDate of Birth: ")
            .append(this.getDateOfBirth())
            .append("\n\tAge: ")
            .append(this.getAgeYears())
            .append("\n\tHealth: ")
            .append(this.getHealth())
            .append("\n\tRage: ")
            .append(this.getRage());

        System.out.println(details.toString());
    };

    //TODO:throw exception on rage < 5
    public int berserk()
    {
        setRage(getRage() + BERSERK_RAGE_INCREASE);

        return (getRage() > MIN_RAGE_FOR_BERSERK_MULTIPLIER) ? BASE_DAMAGE : BASE_DAMAGE * BERSERK_DAMAGE_MULTIPLIER;

    }
}
