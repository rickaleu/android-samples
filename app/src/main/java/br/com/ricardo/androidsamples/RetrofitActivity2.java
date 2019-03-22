package br.com.ricardo.androidsamples;

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

        instructorName = (TextView) findViewById(R.id.udacity_instructor_name);
        instructorBio = (TextView) findViewById(R.id.udacity_instructor_bio);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            instructorName.setText(bundle.getString("InstructorName"));
            instructorBio.setText(bundle.getString("InstructorBio"));
        }

    }
}
