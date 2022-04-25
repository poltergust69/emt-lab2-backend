package com.example.lab2.service.impl;


import com.example.lab2.model.Author;
import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.model.dto.BookDto;
import com.example.lab2.model.dto.CategoryDto;
import com.example.lab2.model.exceptions.AuthorNotFound;
import com.example.lab2.model.exceptions.BookHasNoAvailableCopies;
import com.example.lab2.model.exceptions.BookNotFound;
import com.example.lab2.repository.AuthorRepository;
import com.example.lab2.repository.BookRepository;
import com.example.lab2.repository.CountryRepository;
import com.example.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFound(authorId));
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> editBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
        book.setName(bookDto.getName());
        Author author = authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFound(bookDto.getAuthor()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(Category.valueOf(bookDto.getCategory()));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> saveBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        Author author = authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFound(bookDto.getAuthor()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(Category.valueOf(bookDto.getCategory()));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
        this.bookRepository.delete(book);
    }

    @Override
    public Integer decreaseAvailableCopiesForBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
        Integer availableCopies = book.getAvailableCopies();
        if(availableCopies <= 0)
            throw new BookHasNoAvailableCopies(id);
        availableCopies--;
        book.setAvailableCopies(availableCopies);
        bookRepository.save(book);
        return availableCopies;
    }

    @Override
    public List<CategoryDto> findAllByCategories() {
        List<Book> books = findAll();
        Map<String, Integer> booksByCategory = new HashMap<>();
        for(Category c: Category.values()){
            booksByCategory.putIfAbsent(c.name(), 0);
        }
        for(Book b: books){
            int count = booksByCategory.get(b.getCategory().name()) + 1;
            booksByCategory.put(b.getCategory().name(), count);
        }
        List<CategoryDto> booksByCategoryDTO = new ArrayList<>();
        for(String key: booksByCategory.keySet()){
            booksByCategoryDTO.add(new CategoryDto(key, booksByCategory.get(key)));
        }
        return booksByCategoryDTO;
    }
}

