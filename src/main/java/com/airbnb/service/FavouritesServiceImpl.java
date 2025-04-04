package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Favourites;
import com.airbnb.entity.Property;
import com.airbnb.payload.FavouritesDto;
import com.airbnb.repository.FavouritesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService{

    private FavouritesRepository favouritesRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    public FavouritesDto addFavourites(AppUser user, FavouritesDto favouritesDto, Property property) {
        favouritesDto.setAppUser(user);
        favouritesDto.setProperty(property);
        Favourites savedData = favouritesRepository.save(dtoToEntity(favouritesDto));
        return entityToDto(savedData);
    }

    @Override
    public List<FavouritesDto> getFavourites() {
        List<Favourites> all = favouritesRepository.findAll();
        return all.stream().map(e->entityToDto(e)).toList();
    }

    @Override
    public FavouritesDto updateFavourite(FavouritesDto favouritesDto, long favouriteId) {
        Optional<Favourites> byId = favouritesRepository.findById(favouriteId);
        Favourites favourite=null;
        if (byId.isPresent()){
           favourite = byId.get();
           favourite.setStatus(favouritesDto.getStatus());
            Favourites updatedData = favouritesRepository.save(favourite);
            return entityToDto(updatedData);
        }
        return null;
    }

    Favourites dtoToEntity(FavouritesDto dto){
        Favourites entity = new Favourites();
        entity.setStatus(dto.getStatus());
        entity.setProperty(dto.getProperty());
        entity.setAppUser(dto.getAppUser());
        return entity;
    }

    FavouritesDto entityToDto(Favourites entity){
        FavouritesDto dto = new FavouritesDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setAppUser(entity.getAppUser());
        dto.setProperty(entity.getProperty());
        return dto;
    }
}
