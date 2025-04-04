package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.AddPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airbnb/properties")
public class AddPropertyController {


    private AddPropertyService addPropertyService;

    public AddPropertyController(AddPropertyService addPropertyService) {
        this.addPropertyService = addPropertyService;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> addProperty(@RequestParam Long countryId,
                                                   @RequestParam Long locationId,
                                                   @RequestBody PropertyDto propertyDto){
        PropertyDto addedProperty = addPropertyService.addProperty(countryId, locationId, propertyDto);
        return new ResponseEntity<>(addedProperty, HttpStatus.CREATED);

    }

    @GetMapping("/getProperties")
    public ResponseEntity<List<PropertyDto>> getProperties(){
        List<PropertyDto> properties = addPropertyService.getProperties();
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }

    @GetMapping("/search/properties")
    public ResponseEntity<List<Property>> searchPropertyByLocation(@RequestParam("name") String location){
        List<Property> properties = addPropertyService.searchPropertyByLocation(location);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
}
