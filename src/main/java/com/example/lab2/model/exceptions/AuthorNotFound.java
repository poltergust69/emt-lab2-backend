package com.example.lab2.model.exceptions;

public class AuthorNotFound extends RuntimeException{
    public AuthorNotFound(Long id) {
        super("The author with ID {" + id + "} cannot be found.");
    }
}