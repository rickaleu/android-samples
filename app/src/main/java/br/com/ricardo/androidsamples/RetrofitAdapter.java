package br.com.ricardo.androidsamples;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.ricardo.androidsamples.models.Course;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.UdacityHolder> {

    private List<Course> courses;

    public RetrofitAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public RetrofitAdapter.UdacityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course, viewGroup, false);

        UdacityHolder holder = new UdacityHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitAdapter.UdacityHolder udacityHolder, int i) {

        udacityHolder.courseTitle.setText(courses.get(i).getTitle());
        udacityHolder.courseSubtitle.setText(courses.get(i).getSubtitle());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class UdacityHolder extends RecyclerView.ViewHolder{

        private TextView courseTitle;
        private TextView courseSubtitle;

        public UdacityHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = (TextView) itemView.findViewById(R.id.udacity_title);
            courseSubtitle = (TextView) itemView.findViewById(R.id.udacity_subtitle);

        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
