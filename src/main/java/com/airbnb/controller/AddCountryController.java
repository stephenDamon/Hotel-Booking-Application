package com.airbnb.controller;

import com.airbnb.entity.Country;
import com.airbnb.service.AddCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airbnb/countries")
public class AddCountryController {

    @Autowired
    private AddCountryService addCountryService;
    @PostMapping("/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        Country addedCountry = addCountryService.addCountry(country);
        return new ResponseEntity<>(addedCountry, HttpStatus.CREATED);
    }

    @GetMapping("/getCountries")
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countries = addCountryService.getCountries();
        return new ResponseEntity<>(countries,HttpStatus.OK);
    }
}
