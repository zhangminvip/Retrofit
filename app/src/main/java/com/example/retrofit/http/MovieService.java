package com.example.retrofit.http;

import com.example.retrofit.entity.MovieEntity;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by tom on 2017/6/25.
 */

public interface MovieService {

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
