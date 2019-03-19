package br.com.ricardo.androidsamples;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editIdade;
    private Button buttonGravar;
    private Button buttonLimpar;
    private Button buttonRecuperar;

    private static final String PREFS_NAME = "preferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editNome = (EditText) findViewById(R.id.edit_nome);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editIdade = (EditText) findViewById(R.id.edit_idade);
        buttonGravar = (Button) findViewById(R.id.button_gravar);
        buttonLimpar = (Button) findViewById(R.id.button_limpar);
        buttonRecuperar = (Button) findViewById(R.id.button_recuperar);

        buttonGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DadosPersistidos prefs = new DadosPersistidos(getSharedPreferences(PREFS_NAME, MODE_PRIVATE));

                prefs.salvarDadosString("nome", editNome.getText().toString());
                prefs.salvarDadosString("email", editEmail.getText().toString());
                prefs.salvarDadosInt("idade", Integer.parseInt(editIdade.getText().toString()));

//                SharedPreferences preferences = getSharedPreferences("preferencias", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("nome", editNome.getText().toString());
//                editor.putString("email", editEmail.getText().toString());
//                editor.apply();

                Toast.makeText(Form.this, "Dados gravados com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNome.setText("");
                editEmail.setText("");
                editIdade.setText("");
            }
        });

        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DadosPersistidos prefs = new DadosPersistidos(getSharedPreferences(PREFS_NAME, MODE_PRIVATE));

                editNome.setText(prefs.recuperarDadosString("nome"));
                editEmail.setText(prefs.recuperarDadosString("email"));
                editIdade.setText(String.valueOf(prefs.recuperaDadosInt("idade")));

//                SharedPreferences preferences = getSharedPreferences("preferencias", MODE_PRIVATE);
//                editNome.setText(preferences.getString("nome", "não encontrado"));
//                editEmail.setText(preferences.getString("email", "não encontrado"));
//                editEmail.setText(preferences.getString("idade", "não encontrado"));
            }
        });
    }
}
