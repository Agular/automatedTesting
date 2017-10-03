package weatherapi.exceptions;

public class IllegalArraySizeException extends Exception
{
    public IllegalArraySizeException() {}

    public IllegalArraySizeException(String message)
    {
        super(message);
    }
}
