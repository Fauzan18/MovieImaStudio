package com.mobile.fauzanibnusarky.movieimastudio;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mobile.fauzanibnusarky.movieimastudio.Adapter.AdapterMovieSearch;
import com.mobile.fauzanibnusarky.movieimastudio.Network.ApiService;
import com.mobile.fauzanibnusarky.movieimastudio.Network.ConfigRetrofit;
import com.mobile.fauzanibnusarky.movieimastudio.Pojo.ResponseSearch;
import com.mobile.fauzanibnusarky.movieimastudio.Pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.recycler_movie_search)
    RecyclerView recyclerMovieSearch;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerMovieSearch.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        } else {
            recyclerMovieSearch.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovie();
            }


        });


    }

    private void getMovie() {

        String query = edtSearch.getText().toString();

        if (query.isEmpty()) {
            edtSearch.setError("Tidak Bisa KoSong !");
        } else {
            ApiService service = ConfigRetrofit.getInstance();
            Call<ResponseSearch> call = service.searchMovie(BuildConfig.APIKEY, BuildConfig.LANGUAGE, query);
            call.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {
                    List<ResultsItem> dataMovie = response.body().getResults();
                    AdapterMovieSearch adapterMovie = new AdapterMovieSearch(MainActivity.this, dataMovie);
                    recyclerMovieSearch.setAdapter(adapterMovie);
                }

                @Override
                public void onFailure(Call<ResponseSearch> call, Throwable t) {

                }
            });

        }
    }
}
