package main.java.Exceptions;

public class InvalidReservationIDException extends Exception{
    public InvalidReservationIDException(String errorMessage) {
        super(errorMessage);
    }
}
