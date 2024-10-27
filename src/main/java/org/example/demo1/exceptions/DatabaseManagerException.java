package org.example.demo1.exceptions;

public class DatabaseManagerException extends RuntimeException{
    public DatabaseManagerException(){super();}
    public DatabaseManagerException(String message){super(message);}
    public DatabaseManagerException(String message, Throwable cause){super(message, cause);}
    public DatabaseManagerException(Throwable cause){super(cause);}

}
