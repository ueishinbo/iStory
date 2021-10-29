package com.example.iStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<Fragment> fgLists = new ArrayList<>(3);

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewPager = (ViewPager2) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        //取消滑动
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //导航栏
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homepage:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.postStory:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.personal:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        //viewpager
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        fgLists.add(new HomeFragment());
        fgLists.add(new PostFragment());
        fgLists.add(new PersonalFragment());
        //adapter 设置viewpager2内容
        FragmentStateAdapter fragmentStateAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getItemCount() {
                return fgLists.size();
            }
        };
        viewPager.setAdapter(fragmentStateAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });



    }

}