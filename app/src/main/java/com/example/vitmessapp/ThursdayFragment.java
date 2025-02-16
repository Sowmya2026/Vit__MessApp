package com.example.vitmessapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThursdayFragment extends Fragment {

    public static ThursdayFragment newInstance(String day) {
        ThursdayFragment fragment = new ThursdayFragment();
        Bundle args = new Bundle();
        args.putString("day", day);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thursday, container, false);

        // Find TextViews and set click listeners
        TextView breakfastTextView = rootView.findViewById(R.id.ThtextView2);
        TextView lunchTextView = rootView.findViewById(R.id.Thlunch);
        TextView dinnerTextView = rootView.findViewById(R.id.Thdinner);
        TextView snacksTextView = rootView.findViewById(R.id.Thsnacks);

        breakfastTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Breakfast Menu", "Poori, channa curry, idli, kara chutney, bread butter jam tea coffee milk, omelette", "7.30AM - 9.00AM");
            }
        });

        lunchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Lunch Menu", "rice, phulka, mint rice, aloo gobi masala, curd ,drumstick karakuezhambu, papad/fryums , cabbage poriyal , dal, sugar, salt, lemon, onion, ghaajar ka halwa ", "12.00PM - 2.00PM");
            }
        });

        dinnerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Dinner Menu", "chilli paneer, schezwan fried rice, dal , rice, lemon juice ,  curd, sugar, milk", "7.30PM - 9.30PM");
            }
        });

        snacksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Snacks Menu", "Vada pav, fried onion, chilli, mint chutney, tamarind chutney, TMC", "5.00PM - 6.30PM");
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
