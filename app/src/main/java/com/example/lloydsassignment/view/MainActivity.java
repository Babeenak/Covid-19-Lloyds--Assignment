package com.example.lloydsassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lloydsassignment.R;
import com.example.lloydsassignment.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesList)
    RecyclerView countryList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ListViewModel viewModel;
    private CountryListAdapter adapter=new CountryListAdapter(new ArrayList<>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
        viewModel.refresh();
        swipeRefreshLayout.setRefreshing(false);
        });
        observeViewModel();
    }
        private void observeViewModel(){
        viewModel.countries.observe(this,countryModels -> {
        if(countryModels !=null){
            System.out.println("countryModels="+countryModels);
            countryList.setVisibility(View.VISIBLE);
            adapter.updateCountries(countryModels);
        }
        });
        viewModel.countryLoadError.observe(this,isError -> {
            if(isError!=null){
                listError.setVisibility(isError?View.VISIBLE:View.GONE);
            }
        });
        viewModel.loading.observe(this,isLoading -> {
             if(isLoading!=null){
                  loadingView.setVisibility(isLoading?View.VISIBLE:View.GONE);
             }
            if(isLoading){
                listError.setVisibility(View.GONE);
                countryList.setVisibility(View.GONE);
            }
        });
        }
    }