package com.example.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.entity.MovieEntity;
import com.example.retrofit.http.MovieService;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {


    @BindView(R.id.click_me_BN)
    Button clickMeBN;

    @BindView(R.id.result_TV)
    TextView resultTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.click_me_BN)
    public void click() {
        getMovie();
    }

    private void getMovie() {
        String baseUrl = "http://api.douban.com/v2/movie";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
//        Call<MovieEntity> call = moviceService.getTopMovie(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                    resultTV.setText(t.getMessage());
//            }
//        });

        movieService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieEntity>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultTV.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        resultTV.setText(movieEntity.toString());
                    }
                });
    }

}
