package com.example.vitmessapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import androidx.fragment.app.Fragment;

public class MondayFragment extends Fragment {

    public static MondayFragment newInstance(String day) {
        MondayFragment fragment = new MondayFragment();
        Bundle args = new Bundle();
        args.putString("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday, container, false);

        TextView breakfastTextView = rootView.findViewById(R.id.MtextView2);
        TextView lunchTextView = rootView.findViewById(R.id.Mlunch);
        TextView dinnerTextView = rootView.findViewById(R.id.Mdinner);
        TextView snacksTextView = rootView.findViewById(R.id.Msnacks);

        breakfastTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Breakfast Menu", "Veg stew, idyappam, coconut milk , aloo paratha , curd, bread butter jam, tea coffe, milk, egg burji masala", "7.30AM - 9.00AM");
            }
        });

        lunchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Lunch Menu", "Rice, rasam, phulka, salt, sugar, tomato rice, onion and chilli raitha, gobi 65, fryums, lemon, chilli potato curry", "12.00PM - 2.00PM");
            }
        });

        dinnerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Dinner Menu", "Crispy thin onion dosa, tomato thokku, sambar, mix veg gravy , rice , phulka, pickle, podi, milk, coffee,   , sugar, salt", "7.30PM - 9.30PM");
            }
        });

        snacksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog("Snacks Menu", "Macroni, ketchup, TMC", "5.00PM - 6.30PM");
            }
        });

        return rootView;
    }

    private void showMenuDialog(String title, String menu, String timings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_dialog, null);

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
