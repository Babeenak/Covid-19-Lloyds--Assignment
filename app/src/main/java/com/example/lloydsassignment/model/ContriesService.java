package com.example.lloydsassignment.model;

import com.example.lloydsassignment.di.DaggerApiComponent;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContriesService {

    private static ContriesService instance;

    @Inject
    public   CountriesApi api;

    private ContriesService() {
        DaggerApiComponent.create().Inject(this);
    }

    public  static ContriesService getInstance(){
        if(instance==null){
            instance=new ContriesService();
        }
        return instance;
    }

    public Single<CountryModel> getCountries(){
        System.out.println("list="+api.getCountries().toString());
        //return null;
        return  api.getCountries();
    }
}
