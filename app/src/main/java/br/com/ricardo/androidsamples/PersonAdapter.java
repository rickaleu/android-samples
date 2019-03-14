package br.com.ricardo.androidsamples;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> persons;

    public PersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        PersonViewHolder holder = new PersonViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonViewHolder personViewHolder, int i) {

        personViewHolder.personName.setText(persons.get(i).getName());
        personViewHolder.personAge.setText(persons.get(i).getAge());
        personViewHolder.personPhotoId.setImageResource(persons.get(i).getPhotoId());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


    public class PersonViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView personName;
        TextView personAge;
        ImageView personPhotoId;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.item_card_view);
            personName = (TextView) itemView.findViewById(R.id.item_name);
            personAge = (TextView) itemView.findViewById(R.id.item_age);
            personPhotoId = (ImageView) itemView.findViewById(R.id.item_photoId);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
