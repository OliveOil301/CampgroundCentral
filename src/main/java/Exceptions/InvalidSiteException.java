package main.java.Exceptions;

public class InvalidSiteException extends Exception{

    public InvalidSiteException(String errorMessage) {
        super(errorMessage);
    }
}
