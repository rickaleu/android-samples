package br.com.ricardo.androidsamples;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoLib extends AppCompatActivity {

    private ConstraintLayout constraintPicasso;
    private ImageView imagePicasso;
    private Button buttonPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_lib);

        constraintPicasso = (ConstraintLayout) findViewById(R.id.constraint_picasso);
        imagePicasso = (ImageView) findViewById(R.id.image_picasso);
        buttonPicasso = (Button) findViewById(R.id.button_call_picasso);
        buttonPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callPicasso();
            }
        });
    }

    public void callPicasso(){

        if(checkConnection()){

            Picasso.get().load("https://secure.meetupstatic.com/photos/member/7/5/2/4/highres_248549988.jpeg")
                    .resize(500, 500)
                    .centerCrop()
                    .into(imagePicasso);
        } else {
            Snackbar.make(constraintPicasso, "Conecte a uma rede", Snackbar.LENGTH_LONG)
                    .setAction("OK", null).show();
        }
    }

    public boolean checkConnection(){

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }
}
