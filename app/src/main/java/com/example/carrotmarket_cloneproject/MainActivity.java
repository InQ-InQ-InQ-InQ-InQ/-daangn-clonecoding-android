package com.example.carrotmarket_cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.carrotmarket_cloneproject.chat.ChatFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private TownLifeFragment townLifeFragment;
    private NearbyFragment nearbyFragment;
    private ChatFragment chatFragment;
    private MyCarrotFragment myCarrotFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();


    }

    public void fragmentOnClick(View view) {
        switch (view.getId()) {
            case R.id.llout_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flout_container, homeFragment).commit();
                break;
            case R.id.llout_town_life:
                getSupportFragmentManager().beginTransaction().replace(R.id.flout_container, townLifeFragment).commit();
                break;
            case R.id.llout_nearby:
                getSupportFragmentManager().beginTransaction().replace(R.id.flout_container, nearbyFragment).commit();
                break;
            case R.id.llout_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.flout_container, chatFragment).commit();
                break;
            case R.id.llout_my_carrot:
                getSupportFragmentManager().beginTransaction().replace(R.id.flout_container, myCarrotFragment).commit();
                break;
            default:
                break;
        }
    }

    public void initComponent() {
        homeFragment = new HomeFragment();
        townLifeFragment = new TownLifeFragment();
        nearbyFragment = new NearbyFragment();
        chatFragment = new ChatFragment();
        myCarrotFragment = new MyCarrotFragment();
    }
}