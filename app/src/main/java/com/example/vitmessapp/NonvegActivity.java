package com.example.vitmessapp;
import android.content.res.Resources;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.HorizontalScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Calendar;

public class NonvegActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private int currentPage = 0;
    private HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonveg);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new YourPagerAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Connecting the  ViewPager with TabLayout
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("M");
                            break;
                        case 1:
                            tab.setText("T");
                            break;
                        case 2:
                            tab.setText("W");
                            break;
                        case 3:
                            tab.setText("Th");
                            break;
                        case 4:
                            tab.setText("F");
                            break;
                        case 5:
                            tab.setText("S");
                            break;
                        case 6:
                            tab.setText("Su");
                            break;
                    }
                }).attach();

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        // Setting the current page based on the day of the week
        switch (currentDay) {
            case Calendar.SUNDAY:
                currentPage = 6;
                break;
            case Calendar.MONDAY:
                currentPage = 0;
                break;
            case Calendar.TUESDAY:
                currentPage = 1;
                break;
            case Calendar.WEDNESDAY:
                currentPage = 2;
                break;
            case Calendar.THURSDAY:
                currentPage = 3;
                break;
            case Calendar.FRIDAY:
                currentPage = 4;
                break;
            case Calendar.SATURDAY:
                currentPage = 5;
                break;
        }
        viewPager.setCurrentItem(currentPage);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        // Horizontal Scroll on Page Change
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int tabItemWidth = getResources().getDimensionPixelSize(R.dimen.tab_item_width);
                int scrollPosition = position * tabItemWidth;
                horizontalScrollView.smoothScrollTo(scrollPosition, 0);
            }
        });
        // Automatically switch pages
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == 6) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        handler.postDelayed(update, 3000);
    }
}
