package org.example.demo1.exeptions;

public class NotUniqueISBNException extends Exception {
    public NotUniqueISBNException(String message) {
        super(message);
    }
}
