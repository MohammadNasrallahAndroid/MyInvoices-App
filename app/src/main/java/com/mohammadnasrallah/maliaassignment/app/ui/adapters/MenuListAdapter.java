package com.mohammadnasrallah.maliaassignment.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mohammadnasrallah.maliaassignment.R;
import com.mohammadnasrallah.maliaassignment.domain.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MenuListAdapter extends ListAdapter<MenuItem, MenuListAdapter.MenuViewHolder> {

    private OnItemClickListener listener;

    public MenuListAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<MenuItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<MenuItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = getItem(position);
        holder.title.setText(menuItem.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(menuItem));
    }

    public interface OnItemClickListener {
        void onItemClick(MenuItem menuItem);
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.menu_item);
        }
    }
}

