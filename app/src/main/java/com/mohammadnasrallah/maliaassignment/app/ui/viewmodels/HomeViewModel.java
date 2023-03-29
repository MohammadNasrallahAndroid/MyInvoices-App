package com.mohammadnasrallah.maliaassignment.app.ui.viewmodels;

import com.mohammadnasrallah.maliaassignment.domain.repository.MenuItemRepository;
import com.mohammadnasrallah.maliaassignment.domain.MenuItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MenuItemRepository menuItemRepository;
    private MutableLiveData<List<MenuItem>> menuItemsLiveData;

    public HomeViewModel(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
        menuItemsLiveData = new MutableLiveData<>();
        loadMenuItems();
    }

    public LiveData<List<MenuItem>> getMenuItemsLiveData() {
        return menuItemsLiveData;
    }

    private void loadMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.getMenuItems();
        menuItemsLiveData.setValue(menuItems);
    }
}
