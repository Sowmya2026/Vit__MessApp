package com.example.vitmessapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DefaultFragment extends Fragment {

    public DefaultFragment() {
        // Required empty public constructor
    }

    public static DefaultFragment newInstance() {
        return new DefaultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default, container, false);
    }
}
