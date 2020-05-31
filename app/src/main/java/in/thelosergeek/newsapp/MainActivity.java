package in.thelosergeek.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import in.thelosergeek.newsapp.Model.Articles;
import in.thelosergeek.newsapp.Model.Headlines;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    final String API_KEY = "ed194c2dff8048f782e606d6fde91790";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    EditText Query;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Query = findViewById(R.id.etQuery);
        search = findViewById(R.id.btnsearch);
        swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCountry();
        retreiveJson("",country,API_KEY);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retreiveJson("",country,API_KEY);
            }
        });
        retreiveJson("",country,API_KEY);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Query.getText().toString().equals("")){
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retreiveJson(Query.getText().toString(),country,API_KEY);
                        }
                    });
                    retreiveJson(Query.getText().toString(),country,API_KEY);
                }else{
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retreiveJson("",country,API_KEY);
                        }
                    });
                    retreiveJson("",country,API_KEY);
                }
            }
        });

    }
    public  void retreiveJson(String query,String country, String apikey){

        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
        if(!Query.getText().toString().equals(""))
        {
            call = ApiClient.getInstance().getApi().getSpecificData(query,apikey);
        }
        else
            {
            call = ApiClient.getInstance().getApi().getHeadLines(country,apikey);
        }

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful()&& response.body().getArticles()!=null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
