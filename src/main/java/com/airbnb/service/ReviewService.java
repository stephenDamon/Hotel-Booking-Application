package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(AppUser user, ReviewDto reviewDto, long propertyId);

    List<ReviewDto> getReviewsByUser(AppUser user);

    List<ReviewDto> getReviews();
}
