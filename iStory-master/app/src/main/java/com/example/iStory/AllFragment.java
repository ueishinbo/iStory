package com.example.iStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.iStory.Object.CRUD;
import com.example.iStory.Object.Section;
import com.example.iStory.Object.SharedPre;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static class HomeFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home_page, container, false);
            ViewPager2 viewPager = view.findViewById(R.id.homeViewPager);
            TabLayout tabLayout = view.findViewById(R.id.homeTabLayout);
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(new SectionBodyFragment());
            fragments.add(new AllFragment());
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
                    if(position == 0){
                        tab.setText("Recommends");
                    }
                    else if(position == 1){
                        tab.setText("All");
                    }
                }
            }).attach();
            return view;
        }
    }

    public static class PostFragment extends Fragment {
        List<String> tags;
        long user_ID;
        ArrayAdapter<String> tag_adapter;
        SharedPre littleGetter=new SharedPre();
        EditText enterProposalText;//content
        EditText enterTitleText;//heading
        Button commitBtn;//commit
        Spinner tagSpinner;//tag


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //read info about tags
            littleGetter.saveTagInfo(getContext());
            tags=littleGetter.getTagInfo(getContext());
            //read info about users
            littleGetter.saveUserInfo(getContext());
            user_ID=littleGetter.getUserInfo(getContext());

            View view = inflater.inflate(R.layout.post_page, container, false);
            enterProposalText= view.findViewById(R.id.enterProposalText);
            enterTitleText=view.findViewById(R.id.enterTitleText);
            commitBtn = view.findViewById(R.id.commitBtn);
            //MXY: spinner
            tagSpinner=view.findViewById(R.id.tagSpinner);
            tag_adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,tags);
            tag_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            tagSpinner.setAdapter(tag_adapter);

            commitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //add one section
                    Section new_section=new Section(user_ID,34,tagSpinner.getSelectedItem().toString(),enterTitleText.getText().toString(),enterProposalText.getText().toString(),dateToStr(),0);
                    CRUD op=new CRUD(getContext());
                    op.addSection(new_section);

                    Intent intent = new Intent(getActivity().getApplicationContext(), SectionDetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("section",new_section);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }
            });

            return view;
        }

        //get time
        public String dateToStr(){
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(date);
        }
    }

    public static class PersonalFragment extends Fragment {
        static final int LOG_OUT_REQUEST = 1;
        private Button settingBtn;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.personal_page, container, false);
            settingBtn = view.findViewById(R.id.settingBtn);
            settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),PersonalSettingActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}
