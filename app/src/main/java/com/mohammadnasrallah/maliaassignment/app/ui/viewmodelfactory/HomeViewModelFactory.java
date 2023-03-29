package com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory;

import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.HomeViewModel;
import com.mohammadnasrallah.maliaassignment.domain.repository.MenuItemRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private final MenuItemRepository menuItemRepository;

    public HomeViewModelFactory(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(menuItemRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}


