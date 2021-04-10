package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Hours {
    @JsonAlias("is_open_now")
    public boolean isOpenNow;
    @JsonAlias("hours_type")
    public String hoursType;

    public OperationTime[] open;
}
