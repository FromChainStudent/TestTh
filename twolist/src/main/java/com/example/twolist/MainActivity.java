package com.example.twolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv, lv_pop;
    private List<String> item, pop_list;
    private MyAdapter adapter;
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_item);

        initData();

        adapter = new MyAdapter();
        lv.setAdapter(adapter);

        initListener();

    }

    private void initListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popWind();
                Toast.makeText(MainActivity.this, "弹出popwindow", Toast.LENGTH_SHORT).show();
                lv_pop.setAdapter(new PopAdapter());
                Toast.makeText(MainActivity.this, "适配器", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void popWind() {
        pop = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.item, null);
        pop.setContentView(view);
        lv_pop = (ListView) view.findViewById(R.id.item);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(null);
        pop.showAsDropDown(lv);
        pop.setHeight(5000);
        pop.setWidth(3000);
    }

    private void initData() {
        item = new ArrayList<>();
        item.add("热门");
        item.add("景区");
        item.add("酒店");
        item.add("母婴");
        item.add("潮流");
        pop_list = new ArrayList<>();
        pop_list.add("全部");
        pop_list.add("分类");
        pop_list.add("其他");
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return item.isEmpty() ? 0 : item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(item.get(position));
            tv.setTextSize(18);
            return tv;
        }
    }

    class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pop_list.isEmpty() ? 0 : pop_list.size();
        }

        @Override
        public Object getItem(int position) {
            return pop_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(pop_list.get(position));
            tv.setTextSize(18);
            return tv;
        }
    }
}
