package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class OperationTime {
    public int day;
    public String start;
    public String end;
    @JsonAlias("is_overnight")
    public boolean isOvernight;
}