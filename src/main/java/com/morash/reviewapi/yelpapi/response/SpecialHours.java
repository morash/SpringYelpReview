package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SpecialHours extends OperationTime {
    public String date;
    @JsonAlias("is_closed")
    public boolean isClosed;
}
