package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddCountryServiceImpl implements AddCountryService{

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Country addCountry(Country country) {
        Country savedData = countryRepository.save(country);
        return savedData;
    }

    @Override
    public List<Country> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries;
    }
}
