package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Location {
    public String address1;
    public String address2;
    public String address3;
    public String city;
    @JsonAlias("zip_code")
    public String zipCode;
    public String country;
    public String state;
    @JsonAlias("display_address")
    public String[] displayAddress;
    @JsonAlias("cross_streets")
    public String crossStates;
}
