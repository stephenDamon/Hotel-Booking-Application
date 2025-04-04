package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.payload.FavouritesDto;
import com.airbnb.repository.FavouritesRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.FavouritesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airbnb/favourites")
public class FavouriteController {

    private FavouritesRepository favouritesRepository;
    private PropertyRepository propertyRepository;
    private FavouritesService favouritesService;

    public FavouriteController(FavouritesRepository favouritesRepository, PropertyRepository propertyRepository, FavouritesService favouritesService) {
        this.favouritesRepository = favouritesRepository;
        this.propertyRepository = propertyRepository;
        this.favouritesService = favouritesService;
    }

    @PostMapping("/addFavourites")
public ResponseEntity<?> addFavourites(
        @AuthenticationPrincipal AppUser user,
        @RequestBody FavouritesDto favouritesDto,
        @RequestParam long propertyId
){
        Property property=null;
        Optional<Property> byId = propertyRepository.findById(propertyId);
        if (byId.isPresent()){
            property=byId.get();
            if (favouritesRepository.findByUserAndProperty
                    (user,property)==null){
                FavouritesDto addedFavourite = favouritesService.addFavourites
                        (user, favouritesDto, property);
                return new ResponseEntity<>(addedFavourite,HttpStatus.CREATED);
            }
            return new ResponseEntity<>("already added to favourite list", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    ("property with id: "+propertyId+ " is not present",HttpStatus.BAD_REQUEST);
        }

}

@GetMapping("/getFavourites")
    public ResponseEntity<List<FavouritesDto>> getFavourites(){
    List<FavouritesDto> favourites = favouritesService.getFavourites();
    return new ResponseEntity<>(favourites,HttpStatus.OK);
}

@PostMapping("/updateFavourite")
    public ResponseEntity<?> updateFavourites(
            @RequestBody FavouritesDto favouritesDto,
            @RequestParam long favouriteId
){
    FavouritesDto updatedFavourite = favouritesService.
            updateFavourite(favouritesDto, favouriteId);
    if (updatedFavourite!=null){
        return new ResponseEntity<>(updatedFavourite,HttpStatus.OK);
    }
    return new ResponseEntity<>("record is not updated",HttpStatus.BAD_REQUEST);
}
}


