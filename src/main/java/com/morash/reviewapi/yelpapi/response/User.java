package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User {
    public String id;
    @JsonAlias("profile_url")
    public String profileUrl;
    public String name;
    @JsonAlias("image_url")
    public String imageUrl;
}
