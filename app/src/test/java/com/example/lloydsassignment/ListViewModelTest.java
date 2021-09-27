package com.example.lloydsassignment;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.lloydsassignment.model.ContriesService;
import com.example.lloydsassignment.model.CountryModel;
import com.example.lloydsassignment.viewmodel.ListViewModel;
import com.google.gson.JsonObject;

import net.bytebuddy.implementation.bind.annotation.Morph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

JsonObject mockData;

    @Rule
    public InstantTaskExecutorRule rule=new InstantTaskExecutorRule();

    @Mock
    ContriesService countriesServices;

    @InjectMocks
    ListViewModel listViewModel=new ListViewModel();

    private Single<CountryModel> testSingle;

    public void setUo(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public  void getCountriesSuccess(){
        CountryModel country=new CountryModel(mockData);
        ArrayList<CountryModel> countirsList=new ArrayList<>();
        countirsList.add(country) ;
        Mockito.when(countriesServices.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();

        Assert.assertEquals(1,listViewModel.countries.getValue());
        Assert.assertEquals(1,listViewModel.countryLoadError.getValue());
        Assert.assertEquals(1,listViewModel.loading.getValue());



    }

    @Before
    public void setUpRxSchedulers(){
        Scheduler immediate=new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> {
runnable.run();
                },
                        true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(Scheduler ->  immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(Scheduler->immediate);

    }
}
