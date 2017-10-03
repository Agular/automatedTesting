package weatherapi.exceptions;

public class IllegalArrayOrderException extends Exception
{
    public IllegalArrayOrderException() {}

    public IllegalArrayOrderException(String message)
    {
        super(message);
    }
}