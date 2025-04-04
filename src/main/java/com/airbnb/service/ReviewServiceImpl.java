package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Location;
import com.airbnb.entity.Property;
import com.airbnb.entity.Reviews;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.LocationRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewsRepository reviewsRepository;

    private PropertyRepository propertyRepository;
    public ReviewServiceImpl(ReviewsRepository reviewsRepository, LocationRepository locationRepository, PropertyRepository propertyRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public ReviewDto addReview(AppUser user, ReviewDto reviewDto, long propertyId) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        if (byId.isPresent()){
            Property property = byId.get();
            reviewDto.setProperty(property);
            reviewDto.setAppUser(user);
            Reviews savedData = reviewsRepository.save(dtoToEntity(reviewDto));
            return entityToDto(savedData);
        }
        return null;
    }

    @Override
    public List<ReviewDto> getReviewsByUser(AppUser user) {
        List<Reviews> byUser = reviewsRepository.findByUser(user);
        if (byUser!=null){
            List<ReviewDto> dtoList = byUser.stream().map(e -> entityToDto(e)).toList();
            return dtoList;
        }
        return null;
    }

    @Override
    public List<ReviewDto> getReviews() {
        List<Reviews> all = reviewsRepository.findAll();
        return all.stream().map(e->entityToDto(e)).toList();
    }

    ReviewDto entityToDto(Reviews entity){

        ReviewDto dto = new ReviewDto();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setProperty(entity.getProperty());
        dto.setAppUser(entity.getAppUser());
        return dto;

    }

    Reviews dtoToEntity(ReviewDto dto){
        Reviews entity = new Reviews();
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
        entity.setProperty(dto.getProperty());
        entity.setAppUser(dto.getAppUser());
        return entity;
    }
}
