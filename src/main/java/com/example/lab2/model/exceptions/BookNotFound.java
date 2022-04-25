package com.example.lab2.model.exceptions;

public class BookNotFound extends RuntimeException{
    public BookNotFound(Long id) {
        super("The book with ID {" + id + "} cannot be found.");
    }
}