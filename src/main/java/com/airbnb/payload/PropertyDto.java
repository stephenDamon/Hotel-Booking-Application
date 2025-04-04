package com.airbnb.payload;

import com.airbnb.entity.Country;
import com.airbnb.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {

    private Long id;

    private String name;

    private Integer noGuests;

    private Integer noBathrooms;

    private Integer nightlyPrice;

    private Integer noBedrooms;

    private Country country;

    private Location location;

}
