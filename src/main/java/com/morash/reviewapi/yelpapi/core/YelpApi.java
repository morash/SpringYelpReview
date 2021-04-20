package com.morash.reviewapi.yelpapi.core;

import com.morash.reviewapi.yelpapi.errors.ApiCallsExceededException;
import com.morash.reviewapi.yelpapi.errors.AuthorizationException;
import com.morash.reviewapi.yelpapi.errors.ResourceNotFoundException;
import com.morash.reviewapi.yelpapi.errors.YelpFusionApiException;
import com.morash.reviewapi.yelpapi.response.BusinessDetails;
import com.morash.reviewapi.yelpapi.response.BusinessReviews;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Supplier;
import java.io.IOException;
import java.net.URI;

public class YelpApi {
    private static YelpApi instance;
    private static final String BUSINESS_DETAIL_ENDPOINT = "https://api.yelp.com/v3/businesses/%s";
    private static final String BUSINESS_REVIEW_ENDPOINT = "https://api.yelp.com/v3/businesses/%s/reviews";
    
    private ApiConfig config;

    private YelpApi() {
        config = new ApiConfig();
    }

    public static YelpApi getInstance() {
        if (instance == null) {
            synchronized (YelpApi.class) {
                if (instance == null) {
                    instance = new YelpApi();
                }
            }
        }

        return instance;
    }

    public BusinessDetails getBusinessDetails(String businessId) throws YelpFusionApiException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
            URI.create(String.format(BUSINESS_DETAIL_ENDPOINT, businessId)))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + config.getApiKey())
        .build();

        try {
            HttpResponse<Supplier<BusinessDetails>> body = client.send(request, new JsonBodyHandler<>(BusinessDetails.class));            

            if (body.statusCode() == 401) {
                // Api key authorization fails
                throw new AuthorizationException();
            } else if (body.statusCode() == 404) {
                // Resource not found
                throw new ResourceNotFoundException();
            } else if (body.statusCode() == 429) {
                //Api calls exceed daily limit
                throw new ApiCallsExceededException();
            }

            return body.body().get();
        } catch (IOException|InterruptedException e) {
            System.out.println("Error processing response to Java object");
            e.printStackTrace();
            return null;
        }
    }

    public BusinessReviews getBusinessReviews(String businessId) throws YelpFusionApiException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
            URI.create(String.format(BUSINESS_REVIEW_ENDPOINT, businessId)))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + config.getApiKey())
        .build();

        try {
            HttpResponse<Supplier<BusinessReviews>> body = client.send(request, new JsonBodyHandler<>(BusinessReviews.class));

            if (body.statusCode() == 401) {
                // Api key authorization fails
                throw new AuthorizationException();
            } else if (body.statusCode() == 404) {
                // Resource not found
                throw new ResourceNotFoundException();
            } else if (body.statusCode() == 429) {
                //Api calls exceed daily limit
                throw new ApiCallsExceededException();
            }

            return body.body().get();
        } catch (IOException|InterruptedException e) {
            System.out.println("Error processing response to Java object");
            e.printStackTrace();
            return null;
        }
    }
}
