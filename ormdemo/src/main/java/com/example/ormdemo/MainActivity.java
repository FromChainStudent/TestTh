package com.example.ormdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name, et_pwd;
    private Button btn;
    private String name, pwd;
    private MyHelper helper;
    private Dao<Data, Integer> dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyHelper(this, "data.db", 1);
        helper.getInstance(this);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.name);
        et_pwd = (EditText) findViewById(R.id.pwd);
        btn = (Button) findViewById(R.id.commit);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = et_name.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        try {
            dao = helper.getDao();
            Data data = new Data();
            data.setName(name);
            data.setPwd(pwd);
            dao.create(data);
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
