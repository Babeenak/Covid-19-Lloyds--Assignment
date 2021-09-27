package com.example.lloydsassignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lloydsassignment.di.DaggerApiComponent;
import com.example.lloydsassignment.model.ContriesService;
import com.example.lloydsassignment.model.CountryModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public MutableLiveData<CountryModel> countries=new MutableLiveData<CountryModel>();
    public MutableLiveData<Boolean> countryLoadError= new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading= new MutableLiveData<Boolean>();

    @Inject
    public ContriesService contriesService;

    public ListViewModel (){
        super();
        DaggerApiComponent.create().Inject(this);
    }
private CompositeDisposable disposable=new CompositeDisposable();
    public  void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        loading.setValue(true);
        disposable.add(
                contriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CountryModel>(){

                    @Override
                    public void onSuccess(@NonNull CountryModel countryModels) {
                    countries.setValue(countryModels);
                    countryLoadError.setValue(false);
                    loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
