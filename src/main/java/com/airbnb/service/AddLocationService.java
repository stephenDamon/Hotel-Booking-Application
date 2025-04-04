package com.airbnb.service;

import com.airbnb.entity.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddLocationService {
    Location addLocation(Location location);

    List<Location> getLocations();
}
