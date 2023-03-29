package com.mohammadnasrallah.maliaassignment.app.ui.viewholders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mohammadnasrallah.maliaassignment.R;
import com.mohammadnasrallah.maliaassignment.databinding.LayoutDatePickerBinding;
import com.mohammadnasrallah.maliaassignment.databinding.LayoutTextfieldBinding;

import androidx.annotation.Nullable;

public class DatePickerViewHolder extends LinearLayout {
    private TextInputEditText editText;
    private TextView titleTextView;
    private TextView errorTextView;
    private TextView secondaryLabel;

    private final LayoutDatePickerBinding binding;

    public DatePickerViewHolder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = LayoutDatePickerBinding.inflate(inflater, this, false);
        addView(binding.getRoot());
        editText = binding.datePickerEditText;
        titleTextView = binding.textViewLabel;
        errorTextView = binding.textViewError;
        secondaryLabel = binding.textViewSecondaryLabel;
        editText.setTextColor(getResources().getColor(R.color.text_grey4));

    }

    public View getView() {
        return binding.datePickerEditText;
    }

    public void setSecondaryLabel(String string) {
        secondaryLabel.setText(string);
    }

    public TextView getSecondaryLabelObject() {
        return secondaryLabel;
    }

    public void showSecondaryLabel() {
        secondaryLabel.setVisibility(VISIBLE);
    }

    public void setText(String string) {
        editText.setText(string);
    }

    public void setError(String string) {
        errorTextView.setText(string);
    }

    public void setTitle(String string) {
        titleTextView.setText(string);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setHint(String string) {
        editText.setHint(string);
    }
}

