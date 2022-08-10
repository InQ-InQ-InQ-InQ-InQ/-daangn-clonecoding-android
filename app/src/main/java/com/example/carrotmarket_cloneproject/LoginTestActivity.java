package com.example.carrotmarket_cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginTestActivity extends AppCompatActivity {
/*
    로그인 하고 나서
    내 정보들 넘어오나 테스트 하려고 만든 액티비티

    일단 지금 당장은 안해보고 필요한 정보 생기면 바로 적용하면 될듯
    은찬이가 수정은 금방 한다 했음

 */

    TextView viewId, viewPw, viewName, viewNickname, viewPhone, viewCity, viewTown;
    Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        viewId = findViewById(R.id.textView);
        viewPw = findViewById(R.id.textView2);
        viewName = findViewById(R.id.textView3);
        viewNickname = findViewById(R.id.textView4);
        viewPhone = findViewById(R.id.textView5);
        viewCity = findViewById(R.id.textView6);
        viewTown = findViewById(R.id.textView7);

        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginTestActivity.this, Activity_chat.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();

        String id = intent.getStringExtra("loginId");
        String pw = intent.getStringExtra("loginPw");
        String name = intent.getStringExtra("name");
        String nickname = intent.getStringExtra("nickname");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String city = intent.getStringExtra("city");
        String town = intent.getStringExtra("town");

        viewId.setText(id);
        viewPw.setText(pw);
        viewName.setText(name);
        viewNickname.setText(nickname);
        viewPhone.setText(phoneNumber);
        viewCity.setText(city);
        viewTown.setText(town);

    }
}