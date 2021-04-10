package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class BusinessReviews {
    public int total;
    @JsonAlias("possible_languages")
    public String[] possibleLanguages;
    public Review[] reviews;
}
