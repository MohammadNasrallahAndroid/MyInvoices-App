package com.mohammadnasrallah.maliaassignment.app.ui.viewmodels;

import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;
import com.mohammadnasrallah.maliaassignment.domain.Invoice;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InvoicesViewModel extends ViewModel {
    private InvoiceRepository invoiceRepository;
    private MutableLiveData<List<Invoice>> invoicesLiveData;
    private int customerId;

    public InvoicesViewModel(InvoiceRepository invoiceRepository, int customerId) {
        this.invoiceRepository = invoiceRepository;
        this.customerId = customerId;
        invoicesLiveData = invoiceRepository.getInvoiceList(customerId);
    }

    public LiveData<List<Invoice>> getInvoicesLiveData() {
        return invoicesLiveData;
    }

    public void refreshInvoices() {
        invoiceRepository.refreshInvoiceList(invoicesLiveData, customerId);
    }

}

