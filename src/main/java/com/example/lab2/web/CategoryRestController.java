package com.example.lab2.web;

import com.example.lab2.model.Category;
import com.example.lab2.model.dto.CategoryDto;
import com.example.lab2.service.BookService;
import com.example.lab2.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://frontend-lab2.herokuapp.com/")
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryService categoryService;
    private final BookService bookService;
    public CategoryRestController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("/count")
    public List<CategoryDto> findAllByCount(){
        return bookService.findAllByCategories();
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}
