package com.example.lloydsassignment.di;

import com.example.lloydsassignment.model.ContriesService;
import com.example.lloydsassignment.model.CountriesApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    // public static final String BASE_URL="https://raw.githubusercontent.com/";
    public static final String BASE_URL="https://covid-api.mmediagroup.fr/";

    @Provides
    public CountriesApi provideCountriesApi(){
         return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi.class);
    }

    @Provides
    public ContriesService countriesService(){
        return ContriesService.getInstance();
    }
}
