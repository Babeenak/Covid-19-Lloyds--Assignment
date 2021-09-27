package com.example.lloydsassignment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lloydsassignment.R;
import com.example.lloydsassignment.model.CountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(CountryModel newCountries){
        countries.clear();
        countries.add(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView countryCapital;

        @BindView(R.id.population)
        TextView countryPopulation;

        @BindView(R.id.continent)
        TextView Continent;

        @BindView(R.id.confirmedCases)
        TextView countryConfirmedCases;

        @BindView(R.id.death)
        TextView countryDeath;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(CountryModel country){
            countryName.setText(country.getCountryName().getAsJsonObject().get("country").toString());
            Continent.setText(country.getCountryName().getAsJsonObject().get("continent").toString());
            countryCapital.setText(country.getCountryName().getAsJsonObject().get("capital_city").toString());
            countryPopulation.setText(country.getCountryName().getAsJsonObject().get("population").toString());
            countryConfirmedCases.setText(country.getCountryName().getAsJsonObject().get("confirmed").toString());
            countryDeath.setText(country.getCountryName().getAsJsonObject().get("deaths").toString());


        }

    }
}
