package com.morash.reviewapi;

import com.morash.reviewapi.yelpapi.core.YelpApi;
import com.morash.reviewapi.yelpapi.errors.YelpFusionApiException;
import com.morash.reviewapi.yelpapi.response.BusinessReviews;
import com.morash.reviewapi.yelpapi.response.BusinessDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YelpReviewApplication {
	private static final String BUSINESS_ID = "3jdTCui8Ra9ifEv8JCnUmw";

	public static void main(String[] args) {
		SpringApplication.run(YelpReviewApplication.class, args);
	}

	@GetMapping("/")
	public EndpointResponse apiEndpoint() {
		YelpApi api = YelpApi.getInstance();
		EndpointResponse response = new EndpointResponse();

		BusinessReviews yelpReviews = null;
		BusinessDetails yelpDetails = null;
		
		try {
			yelpDetails = api.getBusinessDetails(BUSINESS_ID);
			yelpReviews = yelpDetails.getReviews();
		} catch (YelpFusionApiException e) {
			System.out.println("Error with request");
			e.printStackTrace();
			throw new ResourceNotFoundException();
		}

		response.reviews = new EndpointReview[yelpReviews.reviews.length];

		for (int i = 0; i < yelpReviews.reviews.length; i++) {
			EndpointReview newReview = new EndpointReview();
			newReview.reviewerName = yelpReviews.reviews[i].user.name;
			newReview.reviewerAvatarUrl = yelpReviews.reviews[i].user.imageUrl;
			newReview.location = yelpDetails.location;
			newReview.rating = yelpReviews.reviews[i].rating;
			newReview.content = yelpReviews.reviews[i].text;

			response.reviews[i] = newReview;
		}

		return response;
	}
}
