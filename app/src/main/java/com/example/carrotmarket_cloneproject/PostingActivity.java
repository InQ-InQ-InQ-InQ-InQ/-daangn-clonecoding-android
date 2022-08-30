package com.example.carrotmarket_cloneproject;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostingActivity extends AppCompatActivity {

    private static final String TAG = "PostingActivity";
    private static final int REQUEST_CODE = 0;
    private Button button_complete;
    private ImageView imageView_gallery;
    private static String mediaPath;
    private static Uri selectedImage;
    private RecyclerView recyclerView;
    private ArrayList<Uri> uriList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        button_complete = (Button) findViewById(R.id.button_complete);
        button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MultipartBody.Part> imageList = new ArrayList<>();
                for(Uri u: uriList) {
                    String path = u.getPath();
                    File file = new File(path);
                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part postingImage =
                            MultipartBody.Part.createFormData("file[]", path, requestFile);
                    imageList.add(postingImage);
                }
                Map<String, RequestBody> map = new HashMap<>();
                RequestBody act = RequestBody.create(MediaType.parse("tet/plain"), "upload");
                map.put("act", act);
                RetrofitAPI retrofit = RetrofitClient.getInstance().create(RetrofitAPI.class);
                final Call<PostingResponse> posting = retrofit.postingRequest(map, imageList);
                posting.enqueue(new Callback<PostingResponse>() {
                    @Override
                    public void onResponse(Call<PostingResponse> call, Response<PostingResponse> response) {
                        Log.e(TAG, "SUCCESS");
                    }

                    @Override
                    public void onFailure(Call<PostingResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        // BEGIN_INCLUDE(initialized recyclerView)
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_image);



        uriList = new ArrayList<>();
        customAdapter = new CustomAdapter(uriList, getApplicationContext());
        recyclerView.setAdapter(customAdapter);
        // END_INCLUDE(initializeRecyclerView)
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        imageView_gallery = (ImageView) findViewById(R.id.ImageView_gallery);
        imageView_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2222);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2222) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
            }
            else {
                if (data.getClipData() == null) {
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList = new ArrayList<>();
                    uriList.add(imageUri);
                    customAdapter.notifyDataSetChanged();
                }
                else {
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    if (clipData.getItemCount() > 10) {
                        Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Log.e(TAG, "multiple choice");
                        uriList = new ArrayList<>();
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri imageUri = clipData.getItemAt(i).getUri();
                            try {
                                uriList.add(imageUri);
                            }
                            catch (Exception e) {
                                Log.e(TAG, "File select error", e);
                            }
                        }
                        customAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}