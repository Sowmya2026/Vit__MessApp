package com.example.vitmessapp;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> items;
    private SparseBooleanArray checkedItems;
    private int maxSelections = 10;

    public RecyclerViewAdapter(List<String> items) {
        this.items = items;
        checkedItems = new SparseBooleanArray();
    }

    public void setMaxSelections(int maxSelections) {
        this.maxSelections = maxSelections;
    }

    public List<String> getSelectedItemsList() {
        List<String> selectedItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (checkedItems.get(i)) {
                selectedItems.add(items.get(i));
            }
        }
        return selectedItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monmenu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.textViewItem.setText(item);
        holder.checkBox.setChecked(checkedItems.get(position));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && getSelectedItemCount() >= maxSelections) {
                buttonView.setChecked(false);
            } else {
                checkedItems.put(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public int getSelectedItemCount() {
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            if (checkedItems.get(i)) {
                count++;
            }
        }
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.textMenuItem);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}

