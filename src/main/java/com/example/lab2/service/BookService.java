package com.example.lab2.service;



import com.example.lab2.model.Book;
import com.example.lab2.model.dto.BookDto;
import com.example.lab2.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(Long authorId);
    Optional<Book> findById(Long id);
    Optional<Book> editBook(Long id, BookDto bookDto);
    Optional<Book> saveBook(BookDto bookDto);
    void deleteById(Long id);
    Integer decreaseAvailableCopiesForBook(Long id);
    List<CategoryDto> findAllByCategories();
}
