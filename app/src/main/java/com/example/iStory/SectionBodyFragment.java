package com.example.iStory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iStory.database.CRUD;
import com.example.iStory.database.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionBodyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<Section> list;
    Context mContext;
    public String type;

    public SectionBodyFragment() {
        //随便推section,Recommends用
        this.type = "";
        this.list = new ArrayList<>();

    }

    public SectionBodyFragment(String type) {
        //指定某一个类别， All用
        this.type = type;
        this.list = new ArrayList<>();

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            void onResume(LifecycleOwner owner){
                //Log.i("_lifecycle",type+" resume");
                refreshListView();
            }

        });
        View view = inflater.inflate(R.layout.recommends_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recommendsRecycler);
        recyclerviewAdapter = new RecyclerviewAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerviewAdapter);
        refreshListView();
        Log.i("asdf", type+" section body fragment refresh");

        recyclerviewAdapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("itemClick0", "onItemClick: " + position);
                Section section = list.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), SectionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("section", section);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }


    public void refreshListView() {
        CRUD op = new CRUD(getContext());
        if (list.size() > 0) list.clear();
        if (type == "") {
            //recommand
            list.addAll(op.getAllSections());
        } else {
            //certain type
            for (Section s : op.getAllSections()) {
                if (s.getTag().toString().equals(type)) {
                    list.add(s);
                }
            }
        }
        if (recyclerviewAdapter.getItemCount() != 0) {

        }
        recyclerviewAdapter.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


    }
}
