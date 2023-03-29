package com.mohammadnasrallah.maliaassignment.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.mohammadnasrallah.maliaassignment.R;
import com.mohammadnasrallah.maliaassignment.domain.repository.InvoiceRepository;
import com.mohammadnasrallah.maliaassignment.domain.repositoryimpl.InvoiceRepositoryImpl;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodelfactory.NewInvoiceViewModelFactory;
import com.mohammadnasrallah.maliaassignment.app.ui.viewmodels.NewInvoiceViewModel;
import com.mohammadnasrallah.maliaassignment.data.DBHelper;
import com.mohammadnasrallah.maliaassignment.databinding.ActivityNewInvoiceBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewInvoiceActivity extends AppCompatActivity {

    private ActivityNewInvoiceBinding binding;
    private NewInvoiceViewModel newInvoiceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int customerId = getIntent().getIntExtra("customer_id", -1);
        InvoiceRepository invoiceRepository = new InvoiceRepositoryImpl(DBHelper.getInstance(getApplicationContext()));
        NewInvoiceViewModelFactory newInvoiceViewModelFactory = new NewInvoiceViewModelFactory(invoiceRepository,customerId);
        newInvoiceViewModel = new ViewModelProvider(this, newInvoiceViewModelFactory).get(NewInvoiceViewModel.class);

        init();
    }

    private void init() {
        binding.txtAmount.setTitle(getString(R.string.amount));
        binding.txtAmount.setHint(getString(R.string.enter_amount));
        binding.datePicker.setTitle(getString(R.string.date));
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(getString(R.string.enter_date));

        builder.setTheme(R.style.MyCalendarTheme);

        MaterialDatePicker datePicker = builder.build();

        binding.datePicker.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                binding.datePicker.setText(sdf.format(calendar.getTime()));
            }
        });


        binding.btnSave.setOnClickListener(view -> {
            String amount = binding.txtAmount.getText();
            String date = binding.datePicker.getText();

            if (!amount.isEmpty() && !date.isEmpty()) {
                boolean isSaved = newInvoiceViewModel.saveInvoice(Double.parseDouble(amount), date);
                if (isSaved) {
                    Toast.makeText(this, R.string.invoice_saved, Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(this, R.string.invoice_save_failed, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
