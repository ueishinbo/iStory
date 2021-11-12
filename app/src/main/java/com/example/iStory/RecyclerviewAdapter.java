package com.example.iStory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iStory.database.Section;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>{
    private Context context;
    //change to section object
    //change from List<String> to List<Section>
    private List<Section> sectionList;
    private OnItemClickListener Listener;

    public RecyclerviewAdapter(Context context,List<Section> data){
        this.context = context;
        this.sectionList = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommends_recycler,parent,false);
        View linearLayout = view.findViewById(R.id.sectionLayout);
        final ViewHolder holder=new ViewHolder(view);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), SectionDetailActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });

        //to further return new ViewHolder(view);-->return holder, to trigger more info in holder while created--such as likes, or comments.
        return holder;
    }

    //set click event for recyclerView
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.Listener=itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        //put list content into view holder
        Section section=sectionList.get(position);
        viewHolder.section_heading.setText(section.getSection_heading());
        viewHolder.section_content.setText(section.getSection_content());
        viewHolder.tag.setText(section.getTag());
        viewHolder.time.setText(section.getTime());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Listener!=null)
                    Listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView section_heading;
        private TextView section_content;
        private TextView time;
        private TextView tag;

        public ViewHolder(View itemView) {
            super(itemView);
            section_heading= itemView.findViewById(R.id.sectionHeadingTextView);
            section_content=itemView.findViewById(R.id.sectionContentTextView);
            time= itemView.findViewById(R.id.sectionTimeTextView);
            tag = itemView.findViewById(R.id.sectionTagTextView);

        }
    }
}
