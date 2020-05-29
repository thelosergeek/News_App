package in.thelosergeek.newsapp;

import in.thelosergeek.newsapp.Model.Headlines;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

        @GET("top-headlines")
        Call<Headlines> getHeadLines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
        );

}
