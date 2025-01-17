
public class Creature {

    private final String name;
    private final Date dateOfBirth;//TODO: Need to define Date. need to be validated
    private final int health;//TODO:  need to be validated
    
    /**
     * Constructor of Creature*/
    
    public Creature(final String name, 
                    final Date dateOfBirth,
                    final int health) //throw exception if parameter are invalid
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
        
    }
    
}