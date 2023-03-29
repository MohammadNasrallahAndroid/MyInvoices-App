package com.mohammadnasrallah.maliaassignment.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohammadnasrallah.maliaassignment.R;
import com.mohammadnasrallah.maliaassignment.domain.Invoice;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class InvoiceAdapter extends ListAdapter<Invoice, InvoiceAdapter.InvoiceViewHolder> {

    public InvoiceAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Invoice> DIFF_CALLBACK = new DiffUtil.ItemCallback<Invoice>() {
        @Override
        public boolean areItemsTheSame(@NonNull Invoice oldItem, @NonNull Invoice newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Invoice oldItem, @NonNull Invoice newItem) {
            return oldItem.getCustomerId() == newItem.getCustomerId()
                    && oldItem.getAmount() == newItem.getAmount()
                    && oldItem.getDate().equals(newItem.getDate());
        }
    };

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Invoice invoice = getItem(position);
        holder.title.setText(String.format(Locale.getDefault(), "Invoice ID: %d", invoice.getId()));
        holder.subtitle.setText(String.format(Locale.getDefault(), "Amount: %.2f, Date: %s", invoice.getAmount(), invoice.getDate()));
    }

    public static class InvoiceViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            subtitle = itemView.findViewById(android.R.id.text2);

            int titleColor = ContextCompat.getColor(itemView.getContext(), R.color.text_grey4);
            title.setTextColor(titleColor);
            subtitle.setTextColor(titleColor);
        }
    }
}

