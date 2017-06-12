package com.example.onlinedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.onlinedemo.bean.Movie;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;

public class InfoActivity extends AppCompatActivity {

    @butterknife.Bind(R.id.iv_back)
    ImageView back;
    @butterknife.Bind(R.id.iv)
    ImageView iv;
    @butterknife.Bind(R.id.tv_name)
    TextView name;
    @butterknife.Bind(R.id.tv_year)
    TextView year;
    @butterknife.Bind(R.id.tv_type)
    TextView type;
    @butterknife.Bind(R.id.tv_language)
    TextView language;
    @butterknife.Bind(R.id.tv_directors)
    TextView directors;
    @butterknife.Bind(R.id.tv_actors)
    TextView actors;
    @Bind(R.id.info_info)
    TextView info;
    @Bind(R.id.btn_near)
    Button near;
    @Bind(R.id.info_error)
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        butterknife.ButterKnife.bind(this);
        initNet();
    }

    private void initNet() {
        String url = HttpUtils.MOVIE + getIntent().getStringExtra("name");
        Log.d("InfoActivity", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.d("InfoActivity", response);
                Movie movie = gson.fromJson(response, Movie.class);
                if (!movie.getResultcode().equals(200)) {
                    List<Movie.ResultBean> result = movie.getResult();
                    Movie.ResultBean resultBean = result.get(2);
                    Glide.with(InfoActivity.this).load(resultBean.getPoster()).into(iv);
                    name.setText(resultBean.getTitle());
                    year.setText(resultBean.getYear());
                    type.setText(resultBean.getGenres());
                    language.setText(resultBean.getLanguage());
                    directors.setText(resultBean.getDirectors());
                    actors.setText(resultBean.getActors());
                    info.setText(resultBean.getPlot_simple());
                } else {
                    error.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    @butterknife.OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
