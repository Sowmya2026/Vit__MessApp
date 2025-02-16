package com.example.vitmessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RepeatOptionAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mOptions;

    public RepeatOptionAdapter(@NonNull Context context, int resource, @NonNull List<String> options) {
        super(context, resource, options);
        mContext = context;
        mOptions = options;
    }

    @Override
    public int getCount() {
        return mOptions.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mOptions.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(mOptions.get(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(mOptions.get(position));

        return convertView;
    }
}
