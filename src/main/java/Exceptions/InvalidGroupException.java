package main.java.Exceptions;

public class InvalidGroupException extends Exception{
    public InvalidGroupException(String errorMessage) {
        super(errorMessage);
    }
}
