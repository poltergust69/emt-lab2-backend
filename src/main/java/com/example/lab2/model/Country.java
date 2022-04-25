package com.example.lab2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    @OneToMany
    private List<Author> authors;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
