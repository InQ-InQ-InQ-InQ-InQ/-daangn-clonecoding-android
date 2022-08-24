package com.example.carrotmarket_cloneproject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {

    @Multipart
    @POST("posting/new")
    Call<String> getUploadArticleRequest(@Part MultipartBody.Part file);

}
