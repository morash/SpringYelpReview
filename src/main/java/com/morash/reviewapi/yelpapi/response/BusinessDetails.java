package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.morash.reviewapi.yelpapi.core.YelpApi;
import com.morash.reviewapi.yelpapi.errors.YelpFusionApiException;

public class BusinessDetails {
    public String id;
    public String alias;
    public String name;
    @JsonAlias("image_url")
    public String imageUrl;
    @JsonAlias("is_claimed")
    public boolean isClaimed;
    @JsonAlias("is_closed")
    public boolean isClosed;
    public String url;
    public String phone;
    @JsonAlias("display_phone")
    public String displayPhone;
    @JsonAlias("review_count")
    public int reviewCount;
    public Category[] categories;
    public float rating;
    public Location location;
    public Coordinates coordinates;
    public String[] photos;
    public String price;
    public Hours[] hours;
    public String[] transactions;
    @JsonAlias("special_hours")
    public SpecialHours[] specialHours;
    public Messaging messaging;

    public BusinessReviews getReviews() throws YelpFusionApiException {
        YelpApi api = YelpApi.getInstance();

        return api.getBusinessReviews(id);
    }
}
