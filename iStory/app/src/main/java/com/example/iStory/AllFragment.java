package com.example.iStory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {
    private List<String> sectionTypes;
    private List<Fragment> fragments;
    private RecyclerView recyclerView;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);
        viewPager = view.findViewById(R.id.allViewPager);
        tabLayout = view.findViewById(R.id.allTabLayout);
        sectionTypes = new ArrayList<>();
        sectionTypes.add("Mystery");
        sectionTypes.add("Historical");
        sectionTypes.add("Love");
        fragments = new ArrayList<>();
        fragments.add(new SectionBodyFragment(sectionTypes.get(0)));
        fragments.add(new SectionBodyFragment(sectionTypes.get(1)));
        fragments.add(new SectionBodyFragment(sectionTypes.get(2)));
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(sectionTypes.get(position));
            }
        }).attach();
        return view;
    }
}
