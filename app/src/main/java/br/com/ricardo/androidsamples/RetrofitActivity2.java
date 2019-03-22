package br.com.ricardo.androidsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RetrofitActivity2 extends AppCompatActivity {

    private TextView instructorName;
    private TextView instructorBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String bio = intent.getStringExtra("BIO");

        instructorName = (TextView) findViewById(R.id.udacity_instructor_name);
        instructorBio = (TextView) findViewById(R.id.udacity_instructor_bio);

        if(name.equals("")){
            instructorName.setText("Vazio");
        } else if (bio.equals("")){
            instructorBio.setText("Vazio");
        } else{
            instructorName.setText(name);
            instructorBio.setText(bio);
        }


    }
}
