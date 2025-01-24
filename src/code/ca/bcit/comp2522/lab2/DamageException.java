package ca.bcit.comp2522.lab2;

/**
 * Exception used when a damage value is invalid.
 *
 * @author Ted Ip
 * @author Liam Pickrell
 * @author Ryan Chu
 * @version 1.0
 */
public class DamageException extends RuntimeException
{
    public DamageException(String message)
    {
        super(message);
    }
}
