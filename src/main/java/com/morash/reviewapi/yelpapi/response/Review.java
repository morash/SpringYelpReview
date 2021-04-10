package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Review {
    public String id;
    public String text;
    public String url;
    public int rating;
    @JsonAlias("time_created")
    public String time_created;
    public User user;
}
