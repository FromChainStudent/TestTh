package com.example.testthree;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView lv;
    private List<ImgBean> list = new ArrayList<>();
    private Dao<ImgBean, Integer> dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        OrmHelper helper = OrmHelper.getInstance(this);
        try {
            dao = helper.getDao();
            for (int i = 0; i < 20; i++){
                ImgBean img = new ImgBean();
                img.setImg("http://h.hiphotos.baidu.com/image/pic/item/902397dda144ad340668b847d4a20cf430ad851e.jpg");
                img.setTitle("标题" + ( i + 1));
                img.setContent("内容");
                dao.create(img);
                list.add(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv.setAdapter(new MyAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    List<ImgBean> imgBeen = dao.queryForAll();
                    String img = imgBeen.get(position).getImg();
                    String title = imgBeen.get(position).getTitle();
                    String content = imgBeen.get(position).getContent();
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    intent.putExtra("img", img);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    class MyAdapter extends BaseAdapter{

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
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_adapter, null);
                holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            ImgBean img = list.get(position);
            Glide.with(MainActivity.this).load(img.getImg()).into(holder.iv);
            holder.title.setText(img.getTitle());
            holder.content.setText(img.getContent());
            return convertView;
        }

        class ViewHolder{
            private ImageView iv;
            private TextView title, content;
        }
    }
}
