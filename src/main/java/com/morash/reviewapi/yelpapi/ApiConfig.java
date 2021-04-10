package com.morash.reviewapi.yelpapi;

import org.apache.logging.log4j.util.PropertiesUtil;

public class ApiConfig {
    private static String propFile = "yelpapi.config";

    private static String clientId = "clientid";
    private static String apiKey = "apikey";
    private static String useCache = "usecache";
    private static String cacheTime = "cachetime";

    private PropertiesUtil props;

    public ApiConfig() {
        props = new PropertiesUtil(propFile);
    }

    public String getClientId() {
        return props.getStringProperty(clientId);
    }

    public String getApiKey() {
        return props.getStringProperty(apiKey);
    }

    public boolean isUseCache() {
        return props.getBooleanProperty(useCache);
    }

    public int getCacheTime() {
        return props.getIntegerProperty(cacheTime, 0);
    }
}
