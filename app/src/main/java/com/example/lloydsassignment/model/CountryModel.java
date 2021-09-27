package com.example.lloydsassignment.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("All")
    JsonObject countryName;

  /*  @SerializedName("capital_city")
    String capital;

    @SerializedName("continent")
    String flag;*/

    public CountryModel(JsonObject countryName/*, String capital, String flag*/) {
        this.countryName = countryName;
       /* this.capital = capital;
        this.flag = flag;*/
    }

    public JsonObject getCountryName() {
        return countryName;
    }

  /*  public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }*/
}
