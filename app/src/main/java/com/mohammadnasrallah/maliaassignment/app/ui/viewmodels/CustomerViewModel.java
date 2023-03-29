package com.mohammadnasrallah.maliaassignment.app.ui.viewmodels;


import com.mohammadnasrallah.maliaassignment.domain.repository.CustomerRepository;
import com.mohammadnasrallah.maliaassignment.domain.Customer;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomerViewModel extends ViewModel {
    private CustomerRepository customerRepository;
    private MutableLiveData<List<Customer>> customersLiveData;

    public CustomerViewModel(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        customersLiveData = new MutableLiveData<>();
        loadCustomers();
    }

    public LiveData<List<Customer>> getCustomersLiveData() {
        return customersLiveData;
    }

    private void loadCustomers() {
        List<Customer> customers = customerRepository.getCustomerList();
        customersLiveData.setValue(customers);
    }
}

