package com.example.lloydsassignment.di;

import com.example.lloydsassignment.model.ContriesService;
import com.example.lloydsassignment.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = ApiModule.class)
public interface ApiComponent {

    void Inject(ContriesService service);

    void Inject(ListViewModel viewModel);

}
