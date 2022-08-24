package com.example.carrotmarket_cloneproject;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "ImageAdapter";
    private ArrayList<Uri> uriList;
    private Context context;

    // 생성자에서 데이터 리스트 객체, Context를 전달받음.
    CustomAdapter(ArrayList<Uri> uriList, Context context) {
        this.uriList = uriList ;
        this.context = context;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView_item;

        public ViewHolder(View itemView) {
            super(itemView);
            // Define clickListener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            imageView_item = itemView.findViewById(R.id.imageView_item);
        }

        public ImageView getImageView() {
            return imageView_item;
        }
    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    // LayoutInflater - XML에 정의된 Resource(자원) 들을 View의 형태로 반환.
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.multi_image_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        CustomAdapter.ViewHolder vh = new CustomAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + (position + 1) + " set");
        Uri imageUri = uriList.get(position) ;
        Glide.with(context)
                .load(imageUri)
                .into(holder.getImageView());
    }

    // Return the size of your uriList (invoked by layout manager)
    @Override
    public int getItemCount() {
        return uriList.size() ;
    }

}