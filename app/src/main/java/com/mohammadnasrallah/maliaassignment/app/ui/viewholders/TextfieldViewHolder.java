package com.mohammadnasrallah.maliaassignment.app.ui.viewholders;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mohammadnasrallah.maliaassignment.databinding.LayoutTextfieldBinding;

import androidx.annotation.Nullable;

public class TextfieldViewHolder extends LinearLayout {

    private TextInputEditText editText;
    private TextView titleTextView;
    private TextView errorTextView;
    private TextView secondaryLabel;

    private final LayoutTextfieldBinding binding;

    public TextfieldViewHolder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = LayoutTextfieldBinding.inflate(inflater, this, false);
        addView(binding.getRoot());
        editText = binding.textField;
        titleTextView = binding.textViewLabel;
        errorTextView = binding.textViewError;
        secondaryLabel = binding.textViewSecondaryLabel;
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
