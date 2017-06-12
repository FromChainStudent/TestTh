package com.example.listdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listdemo.bean.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView lv_left, lv_two, lv_three;
    private List<Data.RootBean.ProvinceBean> province = new ArrayList<>();
    private LvTwoAdapter lvTwoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initView() {
        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_two = (ListView) findViewById(R.id.lv_two);
        lv_three = (ListView) findViewById(R.id.lv_three);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.16.45.37:8080/")
                .build();
        IServer server = retrofit.create(IServer.class);
        Call<Data> call = server.get();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                province = response.body().getRoot().getProvince();
                lv_left.setAdapter(new LeftAdapter());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                t.printStackTrace();
                Log.d("MainActivity", "请求失败");
            }
        });
    }

    private int size;
    private String name;
    private List<String> strList = new ArrayList<>();
    private List<String> str = new ArrayList<>();
    private LvThreeAdapter ada;
    private List<Data.RootBean.ProvinceBean.CityBean.DistrictBean> district = new ArrayList<>();

    private void initListener() {
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                size = province.get(position).getCity().size();
                strList.clear();
                str.clear();
                for (int i = 0; i < size; i++) {
                    name = province.get(position).getCity().get(i).getName();
                    strList.add(name);
                    district = province.get(position).getCity().get(i).getDistrict();
                }
                lvTwoAdapter = new LvTwoAdapter();
                lv_two.setAdapter(lvTwoAdapter);
                lvTwoAdapter.notifyDataSetChanged();
            }
        });

        lv_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str.clear();
                for (int i = 0; i < district.size(); i++) {
                    String name = district.get(i).getName();
                    str.add(name);
                }
                ada = new LvThreeAdapter();
                lv_three.setAdapter(ada);
                ada.notifyDataSetChanged();
            }
        });
    }

    class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return province.isEmpty() ? 0 : province.size();
        }

        @Override
        public Object getItem(int position) {
            return province.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(province.get(position).getName());
            tv.setTextSize(21);
            return tv;
        }
    }

    class LvTwoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return strList.isEmpty() ? 0 : strList.size();
        }

        @Override
        public Object getItem(int position) {
            return strList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(strList.get(position));
            tv.setTextSize(21);
            return tv;
        }
    }

    class LvThreeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return district.isEmpty() ? 0 : district.size();
        }

        @Override
        public Object getItem(int position) {
            return district.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(str.get(position));
            tv.setTextSize(21);
            return tv;
        }
    }
}
