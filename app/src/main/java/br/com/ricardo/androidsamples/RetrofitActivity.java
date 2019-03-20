package br.com.ricardo.androidsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.ricardo.androidsamples.models.Course;
import br.com.ricardo.androidsamples.models.Instructor;
import br.com.ricardo.androidsamples.models.UdacityCatalog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "Ricardo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //Recebendo em uma variável do tipo retrofit o endpoint.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Criado uma instância da interface e atribuindo a ela toodo o conteúdo do atributo retrofit do tipo retrofit.
        UdacityService service = retrofit.create(UdacityService.class);

        //Criando um objeto do tipo que vai receber a Call de UdacityCatalog e atribuindo a ela a chama do método da interface.
        // Que também retorna uma Call de UdacityCatalog.
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        //Pegando o objeto do tipo Call UdacityCatalog pra fazer uma requisição assíncrona.
        //Após a chamada, ele vai retornar uma resposta (seja positiva ou negativa - onResponse) ou uma falha (onFailure).
        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro: " + response.code());

                } else {

                    UdacityCatalog catalog = response.body();

                    for(Course c : catalog.courses){
                        Log.i(TAG, String.format("%s: %s", c.title, c.subtitle));

                        for(Instructor i : c.instructors){
                            Log.i(TAG, i.name);
                        }
                        Log.i(TAG, "-------------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());
            }
        });

    }
}
