package com.mohammadnasrallah.maliaassignment.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mohammadnasrallah.maliaassignment.R;
import com.mohammadnasrallah.maliaassignment.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        binding.txtEmail.setTitle(getString(R.string.username_placeholder));
        binding.txtEmail.setHint(getString(R.string.enter_username));
        binding.txtPassword.setTitle(getString(R.string.password_placeholder));
        binding.txtPassword.setHint(getString(R.string.enter_your_password));
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });

        String[] languages = {getString(R.string.select_language), getString(R.string.english), getString(R.string.french), getString(R.string.german), getString(R.string.italian), getString(R.string.spanish)};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, languages);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.languageSpinner.setAdapter(adapter);
    }

    private void goToHomePage() {
        if (!binding.languageSpinner.getSelectedItem().equals(getString(R.string.select_language))) {
            proceed();
        } else {
            Toast.makeText(getApplicationContext(), R.string.please_select_language, Toast.LENGTH_SHORT).show();
        }
    }

    private void proceed() {
        if (verify()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    private boolean verify() {
        boolean verified = false;
        if (!binding.txtEmail.getText().isEmpty() && !binding.txtPassword.getText().isEmpty()) {
            verified = true;
        } else {
            Toast.makeText(getApplicationContext(), R.string.username_password_not_empty, Toast.LENGTH_SHORT).show();
        }
        if (verified) {
            if (!binding.txtEmail.getText().equals(getString(R.string.admin)) && !binding.txtPassword.getText().equals(getString(R.string.admin))) {
                Toast.makeText(getApplicationContext(), R.string.username_password_dont_match, Toast.LENGTH_SHORT).show();
                verified = false;
            }
        }
        return verified;
    }
}


