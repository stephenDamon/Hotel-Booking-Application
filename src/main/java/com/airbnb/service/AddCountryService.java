package com.airbnb.service;

import com.airbnb.entity.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddCountryService {
    Country addCountry(Country country);

    List<Country> getCountries();
}
