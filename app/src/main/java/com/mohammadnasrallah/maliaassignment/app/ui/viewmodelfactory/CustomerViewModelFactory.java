package com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory;

import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.CustomerViewModel;
import com.mohammadnasrallah.maliaassignment.domain.repository.CustomerRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomerViewModelFactory implements ViewModelProvider.Factory {
    private final CustomerRepository customerRepository;

    public CustomerViewModelFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CustomerViewModel.class)) {
            return (T) new CustomerViewModel(customerRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
