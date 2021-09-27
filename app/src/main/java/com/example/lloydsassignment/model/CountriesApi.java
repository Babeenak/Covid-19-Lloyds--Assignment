package com.example.lloydsassignment.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {

       @GET("v1/cases?country=India")
           // @GET("DevTides/countries/master/countriesV2.son")
    Single<CountryModel> getCountries();

}
