package com.mohammadnasrallah.maliaassignment.domain.repository;

import com.mohammadnasrallah.maliaassignment.domain.Invoice;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface InvoiceRepository {
    MutableLiveData<List<Invoice>> getInvoiceList(int customerId);
    Boolean saveInvoice(double amount, String date, int customerId);
    void refreshInvoiceList(MutableLiveData<List<Invoice>> invoicesLiveData, int customerId);
}

