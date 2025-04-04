package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.entity.Location;
import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.LocationRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddPropertyServiceImpl implements AddPropertyService{


    private CountryRepository countryRepository;

    private LocationRepository locationRepository;

    private PropertyRepository propertyRepository;

    public AddPropertyServiceImpl(CountryRepository countryRepository, LocationRepository locationRepository, PropertyRepository propertyRepository) {
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto addProperty(Long countryId,
                                   Long locationId,
                                   PropertyDto propertyDto) {

        Country country =null;
        Location location = null;

        Optional<Country> byId = countryRepository.findById(countryId);
        if (byId.isPresent()){
             country = byId.get();
        }

        Optional<Location> byId1 = locationRepository.findById(locationId);
        if (byId1.isPresent()){
            location = byId1.get();
        }
        propertyDto.setCountry(country);
        propertyDto.setLocation(location);
        Property savedData = propertyRepository.save(dtoToEntity(propertyDto));
        return entityToDto(savedData);
    }

    @Override
    public List<Property> searchPropertyByLocation(String location) {
        List<Property> propertyByLocation = propertyRepository.searchPropertyByLocation(location);
        return propertyByLocation;
    }

    @Override
    public List<PropertyDto> getProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(e->entityToDto(e)).toList();
    }

    PropertyDto entityToDto(Property entity){
        PropertyDto dto = new PropertyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNoBathrooms(entity.getNoBathrooms());
        dto.setNoBedrooms(entity.getNoBedrooms());
        dto.setNoGuests(entity.getNoGuests());
        dto.setNightlyPrice(entity.getNightlyPrice());
        dto.setCountry(entity.getCountry());
        dto.setLocation(entity.getLocation());
        return dto;


    }

    Property dtoToEntity(PropertyDto dto){
        Property entity = new Property();
        entity.setName(dto.getName());
        entity.setNoBathrooms(dto.getNoBathrooms());
        entity.setNoBedrooms(dto.getNoBedrooms());
        entity.setNoGuests(dto.getNoGuests());
        entity.setNightlyPrice(dto.getNightlyPrice());
        entity.setLocation(dto.getLocation());
        entity.setCountry(dto.getCountry());
        return entity;

    }


}
