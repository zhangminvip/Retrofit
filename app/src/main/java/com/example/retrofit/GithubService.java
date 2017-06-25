package com.example.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tom on 2017/6/25.
 */

public interface GithubService {
    @GET("users/{user}")
    Call<ResponseBody> getUserString(@Path("user") String user);
}
