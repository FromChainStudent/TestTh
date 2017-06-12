package com.example.ormdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private ListView lv;
    private Dao<Data, Integer> dao;
    private List<Data> datas = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        lv = (ListView) findViewById(R.id.lv);

        try {
            dao = MyHelper.getInstance(this).getDao();
            datas = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new MyAdapter();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(InfoActivity.this)
                        .setMessage("删除").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    dao.delete(datas.get(position));
                                    datas.clear();
                                    dao = MyHelper.getInstance(InfoActivity.this).getDao();
                                    datas = dao.queryForAll();
                                    adapter.notifyDataSetChanged();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                dialog.show();
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.isEmpty() ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(InfoActivity.this);
            tv.setText(datas.get(position).getName());
            return tv;
        }
    }
}
