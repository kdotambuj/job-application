package com.akay.jobapplication.review.impl;

import com.akay.jobapplication.company.Company;
import com.akay.jobapplication.company.CompanyService;
import com.akay.jobapplication.review.Review;
import com.akay.jobapplication.review.ReviewRepository;
import com.akay.jobapplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review>reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company  = companyService.getCompanyById(companyId);

        if (company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else return false;

    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {

        if (companyService.getCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);

            reviewRepository.save(updatedReview);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId)!=null &&
        reviewRepository.existsById(reviewId)){

            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);

            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
