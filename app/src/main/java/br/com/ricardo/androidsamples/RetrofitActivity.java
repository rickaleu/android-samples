package br.com.ricardo.androidsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.androidsamples.models.Instructor;
import br.com.ricardo.androidsamples.models.UdacityCatalog;
import br.com.ricardo.androidsamples.models.Course;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity{

    private static final String TAG = "Ricardo";

    private RecyclerView retrofitRecycler;
    private List<Course> courseList;
    private List<Instructor> instructorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        retrofitRecycler = (RecyclerView) findViewById(R.id.udacity_recycler);
        retrofitRecycler.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        retrofitRecycler.setLayoutManager(linearLayoutManager);


        //Recebendo em uma variável do tipo retrofit o endpoint.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Criado uma instância da interface e atribuindo a ela toodo o conteúdo do atributo retrofit do tipo retrofit.
        UdacityService service = retrofit.create(UdacityService.class);

        //Criando um objeto do tipo que vai receber a Call de GloboNews e atribuindo a ela a chama do método da interface.
        // Que também retorna uma Call de GloboNews.
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        //Pegando o objeto do tipo Call GloboNews pra fazer uma requisição assíncrona.
        //Após a chamada, ele vai retornar uma resposta (seja positiva ou negativa - onResponse) ou uma falha (onFailure).
        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro: " + response.code());

                } else {

                    UdacityCatalog catalog = response.body();
                    courseList = new ArrayList<>();
                    instructorList = new ArrayList<>();

                    for(Course c : catalog.courses){
                        courseList.add(new Course(c.title, c.subtitle, c.instructors));

                        for (Instructor i : c.instructors){
                            instructorList.add(new Instructor(i.name, i.bio));
                        }
                    }

                    RetrofitAdapter adapter = new RetrofitAdapter(courseList);
                    retrofitRecycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());
            }
        });

    }
}
