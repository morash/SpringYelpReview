package com.morash.reviewapi.yelpapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Messaging {
    public String url;
    @JsonAlias("use_case_text")
    public String useCaseText;
}
