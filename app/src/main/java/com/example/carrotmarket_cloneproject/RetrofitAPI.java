package com.example.carrotmarket_cloneproject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitAPI {

    @Multipart
    @POST("posting/new")
    Call<PostingResponse> postingRequest(@PartMap Map<String, RequestBody> map, @Part List<MultipartBody.Part> images);
}
