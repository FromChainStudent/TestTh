package com.example.onlinedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlinedemo.bean.LocalInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LocalInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private String localId;
    private List<LocalInfo.ResultBean.ListsBean> list;
    private ImageView iv;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_info);
        localId = getIntent().getStringExtra("id");

        initNet();

        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.local_back);
        iv.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.local_lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initNet() {
        String url = HttpUtils.MOVIE_INFO + localId;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.d("LocalInfoActivity", response);
                LocalInfo localInfo = gson.fromJson(response, LocalInfo.class);
                list = new ArrayList<>();
                list = localInfo.getResult().getLists();
                lv.setAdapter(new LocalAdapter());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    class LocalAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.isEmpty()?0:list.size();
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
            return convertView;
        }
    }
}
