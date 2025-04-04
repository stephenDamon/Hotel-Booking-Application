package com.airbnb.repository;

import com.airbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CountryRepository extends JpaRepository<Country, Long> {
}