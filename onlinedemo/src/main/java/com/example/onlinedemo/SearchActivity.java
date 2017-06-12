package com.example.onlinedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;
    private ListView lv;
    private List<Movie.ResultBean> list = new ArrayList<>();
    private String name;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initNet() {
        String url = HttpUtils.MOVIE + name;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Movie movie = gson.fromJson(response, Movie.class);
                list = movie.getResult();
                lv.setAdapter(new MyAdapter());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    private void initView() {
        et = (EditText) findViewById(R.id.et_poi);
        btn = (Button) findViewById(R.id.btn_poi);
        lv = (ListView) findViewById(R.id.lv_poi);
        iv = (ImageView) findViewById(R.id.search_back);
        iv.setClickable(true);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(SearchActivity.this, "请填写", Toast.LENGTH_SHORT).show();
                } else {
                    initNet();
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, InfoActivity.class);
                Movie.ResultBean resultBean = list.get(position);
                intent.putExtra("name", resultBean.getTitle());
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.isEmpty() ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(SearchActivity.this).inflate(R.layout.lv_adapter, null);
                holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tv_year = (TextView) convertView.findViewById(R.id.tv_year);
                holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                holder.tv_language = (TextView) convertView.findViewById(R.id.tv_language);
                holder.tv_directors = (TextView) convertView.findViewById(R.id.tv_directors);
                holder.tv_actors = (TextView) convertView.findViewById(R.id.tv_actors);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            Movie.ResultBean resultBean = list.get(position);
            Glide.with(SearchActivity.this).load(resultBean.getPoster()).into(holder.iv);
            holder.tv_name.setText(resultBean.getTitle());
            holder.tv_year.setText(resultBean.getYear());
            holder.tv_type.setText(resultBean.getGenres());
            holder.tv_language.setText(resultBean.getLanguage());
            holder.tv_directors.setText(resultBean.getDirectors());
            holder.tv_actors.setText(resultBean.getActors());
            return convertView;
        }

        class Holder {
            private ImageView iv;
            private TextView tv_name, tv_year, tv_type, tv_language, tv_directors, tv_actors;
        }
    }
}
