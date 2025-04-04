package com.airbnb.controller;

import com.airbnb.entity.Location;
import com.airbnb.service.AddLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airbnb/location")
public class AddLocationController {
    @Autowired
    private AddLocationService addLocationService;
    @PostMapping("/addLocation")
    public ResponseEntity<Location> addLocation(@RequestBody Location location){

        Location addedLocation = addLocationService.addLocation(location);
        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
        }
    @GetMapping("/getLocations")
    public ResponseEntity<List<Location>> getLocations(){
        List<Location> locations = addLocationService.getLocations();
        return new ResponseEntity<>(locations,HttpStatus.OK);

    }
}
