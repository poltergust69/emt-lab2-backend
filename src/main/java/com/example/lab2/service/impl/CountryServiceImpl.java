package com.example.lab2.service.impl;

import com.example.lab2.model.Country;
import com.example.lab2.model.exceptions.CountryNotFound;
import com.example.lab2.repository.CountryRepository;
import com.example.lab2.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public List<Country> findByContinent(String continent) {
        return countryRepository.findAllByContinent(continent);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> saveCountry(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> editCountry(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFound(id));
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}

