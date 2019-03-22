package br.com.ricardo.androidsamples;

import br.com.ricardo.androidsamples.models.UdacityCatalog;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityService {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v1/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();


}
