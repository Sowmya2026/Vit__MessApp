package com.example.vitmessapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SaturdayFragment extends Fragment {

    public static SaturdayFragment newInstance(String day) {
        SaturdayFragment fragment = new SaturdayFragment();
        Bundle args = new Bundle();
        args.putString("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saturday, container, false);

        // Find TextViews and set click listeners
        TextView breakfastTextView = rootView.findViewById(R.id.StextView2);
        TextView lunchTextView = rootView.findViewById(R.id.Slunch);
        TextView dinnerTextView = rootView.findViewById(R.id.Sdinner);
        TextView snacksTextView = rootView.findViewById(R.id.Ssnacks);

        breakfastTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Breakfast Menu", "Rava upma, pav baji, chutney, lemon, chopped onion, tea coffee milk butter jam", "7.30AM - 9.30AM");
            }
        });

        lunchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Lunch Menu", "chilli gobi (semi gravy), ghee rice with fried onion and cashew, onion, lemon, raddish sambar, rice ,  curd, phulka, pickle, cucumber , dal,", "12.00PM - 2.00PM");
            }
        });

        dinnerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Dinner Menu", "Chilli idli, egg masala gravy, phulka, cucumber, rasam, gulab jamun, milk", "7.30PM - 9.30PM");
            }
        });

        snacksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Snacks Menu", "Pani puri, sev, chopped onion, tomato, TMC", "5.00PM - 6.30PM");
            }
        });

        return rootView;
    }

    // Method to show menu dialog
    private void showMenuDialog(String title, String menu, String timings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate the custom layout
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_dialog, null);

        // Set title, menu, and timings
        TextView textViewTitle = dialogView.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);

        TextView textViewMenu = dialogView.findViewById(R.id.textViewMenu);
        textViewMenu.setText("Menu: " + menu);

        TextView textViewTimings = dialogView.findViewById(R.id.textViewTimings);
        textViewTimings.setText("Timings: " + timings);

        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
