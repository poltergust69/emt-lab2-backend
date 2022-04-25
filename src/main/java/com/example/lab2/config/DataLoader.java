package com.example.lab2.config;

import com.example.lab2.model.dto.BookDto;
import com.example.lab2.service.AuthorService;
import com.example.lab2.service.BookService;
import com.example.lab2.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private BookService bookService;
    private CountryService countryService;
    private AuthorService authorService;

    public DataLoader(BookService bookService, CountryService countryService, AuthorService authorService) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;

        fillDatabase();
    }

    private void fillDatabase(){
        countryService.saveCountry("Macedonia", "Europe");
        countryService.saveCountry("USA", "North America");
        countryService.saveCountry("Canada", "North America");
        countryService.saveCountry("Argentina", "South America");
        countryService.saveCountry("Brazil", "South America");
        countryService.saveCountry("China", "Asia");
        countryService.saveCountry("Russia", "Asia");
        countryService.saveCountry("Italy", "Europe");
        countryService.saveCountry("Norway", "Europe");

        authorService.saveAuthor("Zack", "Woukley", countryService.findById(1L).get());
        authorService.saveAuthor("Bill", "Effers", countryService.findById(7L).get());
        authorService.saveAuthor("Jack", "Lengyel", countryService.findById(9L).get());
        authorService.saveAuthor("Pete", "Davids", countryService.findById(2L).get());

        bookService.saveBook(new BookDto("Book 1", 1L, "BIOGRAPHY", 1));
        bookService.saveBook(new BookDto("Book 2", 2L, "HISTORY", 3));
        bookService.saveBook(new BookDto("Book 3", 3L, "THRILLER", 8));
        bookService.saveBook(new BookDto("Book 4", 4L, "CLASSICS", 2));
        bookService.saveBook(new BookDto("Book 5", 3L, "BIOGRAPHY", 4));
        bookService.saveBook(new BookDto("Book 6", 2L, "HISTORY", 12));
        bookService.saveBook(new BookDto("Book 7", 1L, "THRILLER", 14));
        bookService.saveBook(new BookDto("Book 8", 2L, "CLASSICS", 7));
        bookService.saveBook(new BookDto("Book 9", 3L, "CLASSICS", 1));
        bookService.saveBook(new BookDto("Book 10", 4L, "THRILLER", 7));
    }
}
