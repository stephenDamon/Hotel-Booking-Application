package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.payload.FavouritesDto;

import java.util.List;

public interface FavouritesService {
    FavouritesDto addFavourites(AppUser user, FavouritesDto favouritesDto, Property property);

    List<FavouritesDto> getFavourites();

    FavouritesDto updateFavourite(FavouritesDto favouritesDto, long favouriteId);
}
