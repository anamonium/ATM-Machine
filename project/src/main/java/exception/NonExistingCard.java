package exception;

public class NonExistingCard extends Exception{
    public NonExistingCard(){super("This card does not exist!");}
}
