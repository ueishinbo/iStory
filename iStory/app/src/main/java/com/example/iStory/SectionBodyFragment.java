package com.example.iStory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SectionBodyFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<String> list;

    public SectionBodyFragment(){
        //随便推section,Recommends用
        this.list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("Section Headings "+i);
        }
    }

    public SectionBodyFragment(String type){
        //指定某一个类别， All用
        this.list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(type+" "+i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommends_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recommendsRecycler);
        recyclerviewAdapter = new RecyclerviewAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerviewAdapter);
        return view;
    }
}
