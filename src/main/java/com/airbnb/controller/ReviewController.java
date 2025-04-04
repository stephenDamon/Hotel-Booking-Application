package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewsRepository;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airbnb/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewService reviewService, ReviewsRepository reviewsRepository, PropertyRepository propertyRepository) {
        this.reviewService = reviewService;
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal AppUser user,
            @RequestBody ReviewDto reviewDto,
            @RequestParam long propertyId
    ){
        if (reviewsRepository.findByUserAndProperty(user,propertyRepository.findById(propertyId).get())==null){
            ReviewDto savedReview = reviewService.addReview(user, reviewDto, propertyId);
            if (savedReview!=null){
                return new ResponseEntity<>(savedReview, HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>("review already given",HttpStatus.OK);
        }

        return new ResponseEntity<>("invalid input",HttpStatus.OK);
    }

    @GetMapping("/getReviewsByUser")
    public ResponseEntity<?> getReviewsByUser(@AuthenticationPrincipal AppUser user){
        List<ReviewDto> reviewsByUser = reviewService.getReviewsByUser(user);
        if (reviewsByUser!=null){
            return new ResponseEntity<>(reviewsByUser,HttpStatus.OK);
        }
        return new ResponseEntity<>("reviews of this user is not present",HttpStatus.OK);
    }
    @GetMapping("/getReviews")
    public ResponseEntity<List<ReviewDto>> getReviews(){
        List<ReviewDto> reviews = reviewService.getReviews();
        return new ResponseEntity<>(reviews,HttpStatus.OK);

    }
}
