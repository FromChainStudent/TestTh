package com.example.onlinedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_address)
    TextView address;
    @Bind(R.id.main_frame)
    FrameLayout frame;
    @Bind(R.id.main_movie)
    RadioButton movie;
    @Bind(R.id.main_local)
    RadioButton local;
    @Bind(R.id.main_title)
    TextView title;
    private FragmentManager manager;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        shared = getSharedPreferences("city", MODE_PRIVATE);
        editor = shared.edit();
        address.setText(shared.getString("name", ""));
        address.setClickable(true);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_frame, new MovieFragment()).commit();
    }

    @OnClick({R.id.main_movie, R.id.main_local, R.id.main_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_movie:
                manager.beginTransaction().replace(R.id.main_frame, new MovieFragment()).commit();
                title.setText("热门影片");
                break;
            case R.id.main_local:
                manager.beginTransaction().replace(R.id.main_frame, new NearFragment()).commit();
                title.setText("附近影院");
                break;
            case R.id.main_address:
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                editor.putString("id", "");
                editor.commit();
                startActivity(intent);
                break;
        }
    }
}
