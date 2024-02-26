package com.akay.jobapplication.review;

import jdk.javadoc.doclet.Reporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>>getAllReviews(@PathVariable Long companyId){
        return  new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String>addReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean added = reviewService.addReview(companyId,review);
        if (added){
            return new ResponseEntity<>("Review Added",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId, @PathVariable Long companyId){
        Review review = reviewService.getReviewById(companyId,reviewId);
        if (review != null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String>updateReviewById(@PathVariable Long reviewId,
                                                  @PathVariable Long companyId,
                                                  @RequestBody Review updatedReview){

        boolean updated = reviewService.updateReviewById(companyId,reviewId,updatedReview);
        if (updated){
            return new ResponseEntity<>("Review Updated",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String>deleteReviewById(@PathVariable Long reviewId,
                                                  @PathVariable Long companyId){
        boolean deleted = reviewService.deleteReviewById(companyId,reviewId);
        if (deleted){
            return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);

    }





}
