package com.example.carrotmarket_cloneproject.sign;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.Volley;
import com.example.carrotmarket_cloneproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id_clear, et_password_clear, et_name_clear, et_nickname_clear, et_phone_clear;
    private Button btn_register_clear;
    private Spinner spinner_city;
    private EditText et_town;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id_clear = findViewById(R.id.et_id_clear);
        et_password_clear = findViewById(R.id.et_password_clear);
        et_name_clear = findViewById(R.id.et_name_clear);
        et_nickname_clear = findViewById(R.id.et_nickname_clear);
        et_phone_clear = findViewById(R.id.et_phone_clear);
        et_phone_clear.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        et_town = findViewById(R.id.et_town);

        spinner_city = findViewById(R.id.spinner_city);
        ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(cityAdapter);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_city.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_register_clear = findViewById(R.id.btn_register_clear);
        btn_register_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginId = et_id_clear.getText().toString();
                String loginPw = et_password_clear.getText().toString();
                String name = et_name_clear.getText().toString();
                String nickname = et_nickname_clear.getText().toString();
                String phoneNumber = et_phone_clear.getText().toString();
                String city = spinner_city.getSelectedItem().toString();
                String town = et_town.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("suc");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(loginId, loginPw, name, nickname, phoneNumber, city, town, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}