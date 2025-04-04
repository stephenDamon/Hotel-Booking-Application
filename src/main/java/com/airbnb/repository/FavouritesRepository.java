package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Favourites;
import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    @Query("select f from Favourites f where f.appUser=:user and f.property=:property")
    Favourites findByUserAndProperty(@Param("user") AppUser user, @Param("property") Property property);
}