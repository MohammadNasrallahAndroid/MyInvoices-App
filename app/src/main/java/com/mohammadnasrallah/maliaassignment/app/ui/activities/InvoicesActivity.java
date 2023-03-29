package com.mohammadnasrallah.maliaassignment.app.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mohammadnasrallah.maliaassignment.app.ui.adapters.InvoiceAdapter;
import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;
import com.mohammadnasrallah.maliaassignment.domain.repositoryimpl.InvoiceRepositoryImpl;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory.InvoicesViewModelFactory;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.InvoicesViewModel;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.databinding.ActivityInvoicesBinding;
import com.mohammadnasrallah.maliaassignment.domain.Invoice;

import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class InvoicesActivity extends AppCompatActivity {

    private ActivityInvoicesBinding binding;
    private int customerId;
    private InvoiceAdapter invoiceAdapter = new InvoiceAdapter();
    private InvoicesViewModel invoicesViewModel;
    private InvoicesViewModelFactory invoicesViewModelFactory;
    private final ActivityResultLauncher<Intent> newInvoiceActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    invoicesViewModel.refreshInvoices();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityInvoicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customerId = getIntent().getIntExtra("customer_id", -1);

        InvoiceRepository invoiceRepository = new InvoiceRepositoryImpl(DBHelper.getInstance(getApplicationContext()));
        invoicesViewModelFactory = new InvoicesViewModelFactory(invoiceRepository,customerId);
        invoicesViewModel = new ViewModelProvider(this, invoicesViewModelFactory).get(InvoicesViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(invoiceAdapter);

        invoicesViewModel.getInvoicesLiveData().observe(this, new Observer<List<Invoice>>() {
            @Override
            public void onChanged(List<Invoice> invoices) {
                invoiceAdapter.submitList(invoices);
            }
        });

        binding.addInvoiceFab.setOnClickListener(view -> {
            Intent intent = new Intent(InvoicesActivity.this, NewInvoiceActivity.class);
            intent.putExtra("customer_id", customerId);
            newInvoiceActivityResultLauncher.launch(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            invoicesViewModel = new ViewModelProvider(this, invoicesViewModelFactory).get(InvoicesViewModel.class);
            invoicesViewModel.getInvoicesLiveData().observe(this, new Observer<List<Invoice>>() {
                @Override
                public void onChanged(List<Invoice> invoices) {
                    invoiceAdapter.submitList(invoices);
                }
            });
        }
    }
}
