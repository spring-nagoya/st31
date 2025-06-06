package com.example.a2025_st42_r03_viewpager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.viewP = findViewById(R.id.viewPager);
        FragmentStateAdapter adapter = new SlidePagerAdapter(this);
        viewP.setAdapter(adapter);
    }

    private static class SlidePagerAdapter extends FragmentStateAdapter {


        private final int pageNum = 3;
        public SlidePagerAdapter (@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new FirstFragment();
            } else if (position == 1) {
                return new SecondFragment(); // Assuming you have a SecondFragment class
            } else if (position == 2) {
                return new ThirdFragment(); // Assuming you have a ThirdFragment class
            } else {
                return new FirstFragment(); // Assuming you have a ThirdFragment class
            }

        }

        @Override
        public int getItemCount() {
            return pageNum; // Return the number of pages in the ViewPager
        }
    }
}