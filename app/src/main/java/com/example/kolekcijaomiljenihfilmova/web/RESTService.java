package com.example.kolekcijaomiljenihfilmova.web;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTService {
    public static final String BASE_URL = "https://www.omdbapi.com";

    public static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIEndpointInterface apiInterface() {

        return getRetrofitInstance().create(APIEndpointInterface.class);
    }
}