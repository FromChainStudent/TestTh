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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlinedemo.bean.Local;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YC on 2017/6/11.
 *
 */

public class NearFragment extends Fragment {
    private ListView lv;
    private List<Local.ResultBean.DataBean> list;
    private SharedPreferences shared;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_near, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shared = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);
        id = shared.getString("id", "");
        lv = (ListView) view.findViewById(R.id.near_lv);
        initNet();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = list.get(position).getId();
                Intent intent = new Intent(getContext(), LocalInfoActivity.class);
                intent.putExtra("id", id1);
                startActivity(intent);
            }
        });
    }

    private void initNet() {
        String url = HttpUtils.SERACH + id;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("NearFragment", response);
                Gson gson = new Gson();
                Local local = gson.fromJson(response, Local.class);
                Local.ResultBean result = local.getResult();
                list = new ArrayList<>();
                list = result.getData();
                lv.setAdapter(new NearAdapter());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    class NearAdapter extends BaseAdapter {

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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.near_lv_adapter, null);
                holder.tv_name = (TextView) convertView.findViewById(R.id.cinemaName);
                holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
                holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                holder.tv_routes = (TextView) convertView.findViewById(R.id.tv_routes);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Local.ResultBean.DataBean local = list.get(position);
            holder.tv_name.setText(local.getCinemaName() + "(" + local.getCityName() + ")");
            holder.tv_address.setText(local.getAddress());
            holder.tv_phone.setText(local.getTelephone());
            holder.tv_routes.setText(local.getTrafficRoutes());
            return convertView;
        }

        class ViewHolder {
            private TextView tv_name, tv_address, tv_phone, tv_routes;
        }
    }
}
