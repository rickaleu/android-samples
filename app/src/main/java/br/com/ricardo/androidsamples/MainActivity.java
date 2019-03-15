package br.com.ricardo.androidsamples;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pega o fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Abre uma transação e adiciona o fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, new MainFragment());
        fragmentTransaction.commit();

        //Substitui um fragment
        //FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        //fragmentTransaction1.replace(R.id.fragment_content, new MainFragment());
        //fragmentTransaction1.commit();

        //Remove um fragment
        //Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_content);
        //FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        //fragmentTransaction2.remove(fragment);
        //fragmentTransaction2.commit();




    }
}
