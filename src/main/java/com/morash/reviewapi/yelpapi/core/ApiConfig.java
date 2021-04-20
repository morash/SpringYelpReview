package com.morash.reviewapi.yelpapi.core;

import org.apache.logging.log4j.util.PropertiesUtil;

public class ApiConfig {
    private static final String PROP_FILE = "yelpapi.config";

    private static final String CLIENT_ID = "clientid";
    private static final String API_KEY = "apikey";
    private static final String USE_CACHE = "usecache";
    private static final String CACHE_TIME = "cachetime";

    private PropertiesUtil props;

    public ApiConfig() {
        props = new PropertiesUtil(PROP_FILE);
    }

    public String getClientId() {
        return props.getStringProperty(CLIENT_ID);
    }

    public String getApiKey() {
        return props.getStringProperty(API_KEY);
    }

    public boolean isUseCache() {
        return props.getBooleanProperty(USE_CACHE);
    }

    public int getCacheTime() {
        return props.getIntegerProperty(CACHE_TIME, 0);
    }
}
