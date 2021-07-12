package com.example.testapp.Controller;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://104.154.118.39/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
