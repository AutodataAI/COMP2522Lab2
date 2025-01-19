/**
 * Custom checked exception to throw when the Fire Power of 
 * a Dragon is below 10.
 * 
 * @author Liam Pickrell
 * @version 1.0
 */
public class LowFirePowerException extends Exception
{
    public LowFirePowerException(String message)
    {
        super(message);
    }
}
