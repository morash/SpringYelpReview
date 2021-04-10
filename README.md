# YELP_REVIEW

## About

Project created using Spring Boot to connect to Yelp Fusion API and get reviews for a company.

## Set-up
### Setting up API config
1. Create file named 'yelpapi.config' in src/main/resources
2. Add the following into the file, replacing 'YelpFusionApiKey' with your Yelp Fusion API key:
```
apikey=YelpFusionApiKey
```

## Execution
Run as spring boot project using the command:
> ./mvnw spring-boot:run