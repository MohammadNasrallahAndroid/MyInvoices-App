package com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory;

import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.NewInvoiceViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewInvoiceViewModelFactory implements ViewModelProvider.Factory {
    private final InvoiceRepository invoiceRepository;
    private  int customerId;

    public NewInvoiceViewModelFactory(InvoiceRepository invoiceRepository,int customerId) {
        this.invoiceRepository = invoiceRepository;
        this.customerId = customerId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewInvoiceViewModel.class)) {
            return (T) new NewInvoiceViewModel(invoiceRepository,customerId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}