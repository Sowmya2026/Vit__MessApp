package com.example.vitmessapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WednesdayFragment extends Fragment {

    public static WednesdayFragment newInstance(String day) {
        WednesdayFragment fragment = new WednesdayFragment();
        Bundle args = new Bundle();
        args.putString("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wednesday, container, false);

        // Find TextViews and set click listeners
        TextView breakfastTextView = rootView.findViewById(R.id.WtextView2);
        TextView lunchTextView = rootView.findViewById(R.id.Wlunch);
        TextView dinnerTextView = rootView.findViewById(R.id.Wdinner);
        TextView snacksTextView = rootView.findViewById(R.id.Wsnacks);

        breakfastTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Breakfast Menu", "sprouts, poha , pongal, coconut chutney, vada, sambar, bread butter jam, tea coffee milk , omelette", "7.30AM - 9.00AM");
            }
        });

        lunchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Lunch Menu", "Rice,rasam, ghee rice with fried onion and cashews, Chetinad Chicken pepper fry , chapathi,veg salna, onion, pickle , curd, sugar, salt, lemon, onion, lemon juice, pappad", "12.00PM - 2.00PM");
            }
        });

        dinnerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Dinner Menu", "green dal, drumstick sambar, rice, phulka, methi malai matar gravy,  kovakkai poriyal, watermelon, butter milk, milk", "7.30PM - 9.30PM");
            }
        });

        snacksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Snacks Menu", "Dry cake (1 per person), TMC", "5.00PM - 6.30PM");
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
