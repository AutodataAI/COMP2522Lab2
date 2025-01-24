package ca.bcit.comp2522.lab2;
/**
 * Exception used when a healing value is invalid.
 *
 * @author Ted Ip
 * @author Liam Pickrell
 * @author Ryan Chu
 * @version 1.0
 */
public class HealingException extends RuntimeException
{
    public HealingException(String message)
    {
        super(message);
    }
}
