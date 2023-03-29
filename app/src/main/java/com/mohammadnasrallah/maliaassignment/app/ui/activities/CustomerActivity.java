package com.mohammadnasrallah.maliaassignment.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.mohammadnasrallah.maliaassignment.app.ui.adapters.CustomerAdapter;
import com.mohammadnasrallah.maliaassignment.domain.repository.CustomerRepository;
import com.mohammadnasrallah.maliaassignment.domain.repositoryimpl.CustomerRepositoryImpl;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory.CustomerViewModelFactory;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.CustomerViewModel;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.databinding.ActivityCustomerBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


public class CustomerActivity extends AppCompatActivity {

    private ActivityCustomerBinding binding;
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomerRepository customerRepository = new CustomerRepositoryImpl(DBHelper.getInstance(getApplicationContext()));
        CustomerViewModelFactory customerViewModelFactory = new CustomerViewModelFactory(customerRepository);
        customerViewModel = new ViewModelProvider(this, customerViewModelFactory).get(CustomerViewModel.class);
        loadCustomers();

        CustomerAdapter customerAdapter = new CustomerAdapter(customer -> {
            Intent intent = new Intent(CustomerActivity.this, InvoicesActivity.class);
            intent.putExtra("customer_id", customer.getId());
            startActivity(intent);
        });

        binding.recyclerView.setAdapter(customerAdapter);
    }

    private void loadCustomers() {
        customerViewModel.getCustomersLiveData().observe(this, customers -> {
            CustomerAdapter adapter = (CustomerAdapter) binding.recyclerView.getAdapter();
            if (adapter != null) {
                adapter.submitList(customers);
            }
        });
    }
}
