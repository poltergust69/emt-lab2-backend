package com.example.lab2.service;



import com.example.lab2.model.Author;
import com.example.lab2.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> findByNameAndSurname(String name, String surname);
    Optional<Author> editAuthor(Long id, String name, String surname, Country country);
    Optional<Author> saveAuthor(String name, String surname, Country country);
    void deleteById(Long id);
}
