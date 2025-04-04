package com.airbnb.payload;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Location;
import com.airbnb.entity.Property;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    private Integer rating;

    private String description;

    private Property property;

    private AppUser appUser;
}
