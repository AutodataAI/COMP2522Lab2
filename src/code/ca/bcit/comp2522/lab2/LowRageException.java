package ca.bcit.comp2522.lab2;

/**
 * Exception used when an orc's rage value is invalid.
 *
 * @author Ted Ip
 * @author Liam Pickrell
 * @author Ryan Chu
 * @version 1.0
 */
public class LowRageException extends RuntimeException
{
    public LowRageException(String message)
    {
        super(message);
    }
}
