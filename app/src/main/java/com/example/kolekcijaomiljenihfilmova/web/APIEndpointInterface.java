package com.example.kolekcijaomiljenihfilmova.web;

import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.Map;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIEndpointInterface {
    @GET("/")
    Call<Result> getMoviesByTitle(@QueryMap Map<String, String> options);

    @GET("/")
    Call<Movie> getMoviesDetails(@QueryMap Map<String, String> options);
}