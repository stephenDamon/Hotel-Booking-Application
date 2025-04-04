package com.airbnb.payload;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class FavouritesDto {
    private Long id;

    private Boolean status = false;

    private AppUser appUser;

    private Property property;
}
