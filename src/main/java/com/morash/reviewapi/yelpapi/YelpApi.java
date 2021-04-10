package com.morash.reviewapi.yelpapi;

import com.morash.reviewapi.yelpapi.response.BusinessDetails;
import com.morash.reviewapi.yelpapi.response.BusinessReviews;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Supplier;
import java.net.URI;

public class YelpApi {
    private static YelpApi instance;
    private static String businessDetailEndpoint = "https://api.yelp.com/v3/businesses/%s";
    private static String businessReviewsEndpoint = "https://api.yelp.com/v3/businesses/%s/reviews";
    
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

    public BusinessDetails getBusinessDetails(String businessId) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
            URI.create(String.format(businessDetailEndpoint, businessId)))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + config.getApiKey())
        .build();

        try {
            HttpResponse<Supplier<BusinessDetails>> body = client.send(request, new JsonBodyHandler<>(BusinessDetails.class));

            return body.body().get();
        } catch (Exception e) {
            System.out.println("Error with request");
            e.printStackTrace();
            return null;
        }
    }

    public BusinessReviews getBusinessReviews(String businessId) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
            URI.create(String.format(businessReviewsEndpoint, businessId)))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + config.getApiKey())
        .build();

        try {
            HttpResponse<Supplier<BusinessReviews>> body = client.send(request, new JsonBodyHandler<>(BusinessReviews.class));

            return body.body().get();
        } catch (Exception e) {
            System.out.println("Error with request");
            e.printStackTrace();
            return null;
        }
    }
}
