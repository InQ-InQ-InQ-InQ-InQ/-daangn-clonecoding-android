package com.example.carrotmarket_cloneproject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "https://1af1-115-143-100-251.ngrok.io/members/join";
    private Map<String, String> map;

    public RegisterRequest(String loginId, String loginPw, String name, String nickname, String phoneNumber, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("loginId", loginId);
        map.put("pw", loginPw);
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("phoneNumber", phoneNumber);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}