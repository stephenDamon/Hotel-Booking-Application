package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddPropertyService {
    PropertyDto addProperty(Long countryId, Long locationId, PropertyDto propertyDto);

    List<Property> searchPropertyByLocation(String locationName);

    List<PropertyDto> getProperties();
}
