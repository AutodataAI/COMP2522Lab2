
/**
 * Custom checked exception to throw when the Mana of 
 * a Elf is below 5.
 *
 * @author Liam Pickrell
 * @version 1.0
 */
public class LowManaException extends Exception
{
    public LowManaException(String message)
    {
        super(message);
    }
}
