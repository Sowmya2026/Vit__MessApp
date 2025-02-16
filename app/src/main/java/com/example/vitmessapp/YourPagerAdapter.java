package com.example.vitmessapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class YourPagerAdapter extends FragmentStateAdapter {

    public YourPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return MondayFragment.newInstance("Monday");
            case 1:
                return TuesdayFragment.newInstance("Tuesday");
            case 2:
                return WednesdayFragment.newInstance("Wednesday");
            case 3:
                return ThursdayFragment.newInstance("Thursday");
            case 4:
                return FridayFragment.newInstance("Friday");
            case 5:
                return SaturdayFragment.newInstance("Saturday");
            case 6:
                return SundayFragment.newInstance("Sunday");
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

}
