package com.olczyk.android.retrofitsample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostActionRetrofit {

    @GET("posts")
    Call<List<Post>> getPosts();
}
