package com.mohammadnasrallah.maliaassignment.app.ui.viewmodels;

import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;

import androidx.lifecycle.ViewModel;

public class NewInvoiceViewModel extends ViewModel {
    private InvoiceRepository invoiceRepository;
    private int customerId;

    public NewInvoiceViewModel(InvoiceRepository invoiceRepository, int customerId) {
        this.invoiceRepository = invoiceRepository;
        this.customerId = customerId;
    }

    public boolean saveInvoice(double amount, String date) {
        return invoiceRepository.saveInvoice(amount,date,customerId);
    }
}
