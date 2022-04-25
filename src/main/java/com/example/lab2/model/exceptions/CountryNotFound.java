package com.example.lab2.model.exceptions;

public class CountryNotFound extends RuntimeException{
    public CountryNotFound(Long id) {
        super("Country with ID {" + id + "} cannot be found.");
    }
}
