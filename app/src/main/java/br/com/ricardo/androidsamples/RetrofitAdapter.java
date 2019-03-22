package br.com.ricardo.androidsamples;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.androidsamples.models.Course;
import br.com.ricardo.androidsamples.models.Instructor;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.UdacityHolder> {

    private List<Course> courseList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public RetrofitAdapter(List<Course> course) {
        this.courseList = course;
    }

    @NonNull
    @Override
    public RetrofitAdapter.UdacityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_udacity, viewGroup, false);

        UdacityHolder holder = new UdacityHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitAdapter.UdacityHolder udacityHolder, int i) {

        Course c = courseList.get(i);

        udacityHolder.courseTitle.setText(c.title);
        udacityHolder.courseSubtitle.setText(c.subtitle);

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    public class UdacityHolder extends RecyclerView.ViewHolder{

        private TextView courseTitle;
        private TextView courseSubtitle;


        public UdacityHolder(@NonNull final View itemView) {
            super(itemView);

            courseTitle = (TextView) itemView.findViewById(R.id.udacity_title);
            courseSubtitle = (TextView) itemView.findViewById(R.id.udacity_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener != null){
                        if(getAdapterPosition() != RecyclerView.NO_POSITION){
                            mListener.onItemClick(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
