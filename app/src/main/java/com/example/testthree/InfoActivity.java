package com.example.testthree;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private ViewPager vp;
    private TextView tv;
    private List<ImageView> list = new ArrayList<>();
    private int COUNT = Integer.MAX_VALUE / 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    vp.setCurrentItem(COUNT++);
                    handler.sendEmptyMessageDelayed(1, 4000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        vp = (ViewPager) findViewById(R.id.vp);
        tv = (TextView) findViewById(R.id.info_content);

        final String img = getIntent().getStringExtra("img");
        String content = getIntent().getStringExtra("content");
        tv.setText(content);

        final ImageView iv;

        iv = new ImageView(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(InfoActivity.this).load(img).into(iv);
                    }
                });
            }
        }).start();
        list.add(iv);
        list.add(iv);
        list.add(iv);

        vp.setAdapter(new MyAdapter());

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(1, 4000);
            }
        }).start();
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (container != null) {
                container.removeView(list.get(position % list.size()));
            }
            container.addView(list.get(position % list.size()));
            return list.get(position % list.size());
        }
    }
}
