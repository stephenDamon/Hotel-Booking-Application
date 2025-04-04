package com.airbnb.payload;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import lombok.Data;

@Data
public class BookingDto {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private Integer totalNights;

    private Double price;

    private AppUser appUser;

    private Property property;
}
