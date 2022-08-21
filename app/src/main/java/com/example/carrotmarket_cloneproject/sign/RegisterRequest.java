package com.example.carrotmarket_cloneproject.sign;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "https://d3b1-210-103-3-198.ngrok.io/members/join";
    private Map<String, String> map;

    public RegisterRequest(String loginId, String loginPw, String name, String nickname, String phoneNumber, String city, String town, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("loginId", loginId);
        map.put("loginPw", loginPw);
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("phoneNumber", phoneNumber);
        map.put("city", city);
        map.put("town", town);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}