package com.airbnb.service;

import com.airbnb.entity.Location;
import com.airbnb.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddLocationServiceImpl implements AddLocationService{


    @Autowired
    private LocationRepository locationRepository;
    @Override
    public Location addLocation(Location location) {
        Location savedData = locationRepository.save(location);
        return savedData;
    }

    @Override
    public List<Location> getLocations() {
        List<Location> listOfLocations = locationRepository.findAll();
        return listOfLocations;
    }
}
