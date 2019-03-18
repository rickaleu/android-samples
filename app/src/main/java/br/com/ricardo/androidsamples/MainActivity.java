package br.com.ricardo.androidsamples;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements FragmentA.Comunicador {

    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container_fragment_1, new FragmentA());
        ft.commit();


    }

    @Override
    public void insereTexto(String texto) {

        textResultado = findViewById(R.id.resultado);
        textResultado.setText(texto);
    }
}
