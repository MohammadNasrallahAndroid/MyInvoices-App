package com.mohammadnasrallah.maliaassignment.app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mohammadnasrallah.maliaassignment.domain.repository.MenuItemRepository;
import com.mohammadnasrallah.maliaassignment.domain.repositoryimpl.MenuItemRepositoryImpl;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory.HomeViewModelFactory;
import  com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.HomeViewModel;
import com.mohammadnasrallah.maliaassignment.app.ui.adapters.MenuListAdapter;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.databinding.ActivityHomeBinding;
import com.mohammadnasrallah.maliaassignment.domain.MenuItem;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl(DBHelper.getInstance(getApplicationContext()));
        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(menuItemRepository);
        HomeViewModel homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenuListAdapter menuAdapter = new MenuListAdapter(menuItem -> {
            if ("Customers".equals(menuItem.getTitle())) {
                startActivity(new Intent(HomeActivity.this, CustomerActivity.class));
            } else {
                Toast.makeText(this, "Feature not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        binding.recyclerView.setAdapter(menuAdapter);
        homeViewModel.getMenuItemsLiveData().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                updateUI(menuItems);
            }
        });

    }
    private void updateUI(List<MenuItem> menuItems) {
        MenuListAdapter menuAdapter = new MenuListAdapter(menuItem -> {
            if ("Customers".equals(menuItem.getTitle())) {
                startActivity(new Intent(HomeActivity.this, CustomerActivity.class));
            } else {
                Toast.makeText(this, "Feature not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        binding.recyclerView.setAdapter(menuAdapter);
        menuAdapter.submitList(menuItems);
    }

}
