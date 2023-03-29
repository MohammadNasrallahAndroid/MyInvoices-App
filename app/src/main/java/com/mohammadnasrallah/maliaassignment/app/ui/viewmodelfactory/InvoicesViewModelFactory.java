package com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory;

import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.InvoicesViewModel;
import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InvoicesViewModelFactory implements ViewModelProvider.Factory {
    private final InvoiceRepository invoiceRepository;
    private  int customerId;

    public InvoicesViewModelFactory(InvoiceRepository invoiceRepository,int customerId) {
        this.invoiceRepository = invoiceRepository;
        this.customerId = customerId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InvoicesViewModel.class)) {
            return (T) new InvoicesViewModel(invoiceRepository,customerId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
