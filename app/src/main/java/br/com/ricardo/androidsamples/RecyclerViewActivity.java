package br.com.ricardo.androidsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        personList = new ArrayList<>();
        personList.add(new Person("Ricardo", "34", R.drawable.ic_launcher_background));
        personList.add(new Person("Katia", "28", R.drawable.ic_launcher_background));
        personList.add(new Person("Ravene", "31", R.drawable.ic_launcher_background));
        personList.add(new Person("Luzia", "68", R.drawable.ic_launcher_background));
        personList.add(new Person("Eliel", "38", R.drawable.ic_launcher_background));
        personList.add(new Person("Najara", "22", R.drawable.ic_launcher_background));
        personList.add(new Person("Layne", "21", R.drawable.ic_launcher_background));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        PersonAdapter adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);

    }

}
