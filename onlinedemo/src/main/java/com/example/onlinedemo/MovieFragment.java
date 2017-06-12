package com.example.onlinedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.onlinedemo.bean.HostMovie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YC on 2017/6/11.
 */

public class MovieFragment extends Fragment {
    private EditText et;
    private GridView grid;
    private List<HostMovie.ResultBean> list;
    private SharedPreferences shared;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et = (EditText) view.findViewById(R.id.movie_et);
        grid = (GridView) view.findViewById(R.id.movie_grid);
        shared = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);

        initNet();

        initListener();
    }

    private void initListener() {
        et.setClickable(true);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movieId = list.get(position).getMovieId();
                String movieName = list.get(position).getMovieName();
                Intent intent = new Intent(getContext(), InfoActivity.class);
                intent.putExtra("name", movieName);
                Log.d("MovieFragment", movieName);
                startActivity(intent);
            }
        });
    }

    private void initNet() {
        String url = HttpUtils.MOVIE_TODAY + shared.getString("id", "");
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Gson gson = new Gson();
                    HostMovie hostMovie = gson.fromJson(response, HostMovie.class);
                    list = new ArrayList<>();
                    list = hostMovie.getResult();
                    grid.setAdapter(new MovieAdapter());
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, null);
    }

    class MovieAdapter extends BaseAdapter {

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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_adapter, null);
                holder.iv = (ImageView) convertView.findViewById(R.id.movie_iv);
                holder.tv = (TextView) convertView.findViewById(R.id.movie_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            HostMovie.ResultBean movie = list.get(position);
            Glide.with(getContext()).load(movie.getPic_url()).into(holder.iv);
            holder.tv.setText(movie.getMovieName());
            return convertView;
        }

        class ViewHolder {
            private ImageView iv;
            private TextView tv;
        }
    }
}
