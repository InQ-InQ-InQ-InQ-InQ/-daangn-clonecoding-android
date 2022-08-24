package com.example.carrotmarket_cloneproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

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

        // BEGIN_INCLUDE(initialized recyclerView)
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_image);

        button_complete = (Button) findViewById(R.id.button_complete);
//        button_complete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//                MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", imageFileName, requestFile);
//                RetrofitAPI retrofitAPI=RetrofitClient.getInstance().create(RetrofitAPI.class);
//                Call<String> call=retrofitAPI.getUploadArticleRequest(body);
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        Log.e("uploadArticle", "SUCCESS : ");
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        Log.e("uploadArticle", "ERROR : " + t.getMessage());
//                    }
//                });
//            }
//        });
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