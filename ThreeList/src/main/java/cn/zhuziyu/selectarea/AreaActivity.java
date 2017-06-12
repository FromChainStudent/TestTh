package cn.zhuziyu.selectarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import cn.zhuziyu.selectarea.model.CityData;
import cn.zhuziyu.selectarea.model.DistrictData;
import cn.zhuziyu.selectarea.model.ProvinceData;

/**
 * @author Zen
 * @date 2016/3/27 0027 15:05
 */
public class AreaActivity extends AppCompatActivity {


    @Bind(R.id.lv_city)
    ListView lvCity;
    @Bind(R.id.lv_pro)
    ListView lvPro;
    @Bind(R.id.lv_dis)
    ListView lvDis;

    private InputStreamReader reader;
    private Gson gson;

    private List<ProvinceData> proData;
    private List<CityData> cityData, cityCheckData;   //前者放置所有的城市数据，后者放置根据条件选择的城市数据
    private List<DistrictData> disData, disCheckData;//同上

    private List<String> proDatas;
    private List<String> cityDatas;
    private List<String> disDatas;

    private ArrayAdapter<String> cityAdapter, disAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        gson = new Gson();
        initView();
    }

    protected void initView() {
        //刚进来的时候，立刻给省的ListView填充数据
        try {
            Type proType = new TypeToken<ArrayList<ProvinceData>>() {
            }.getType();
            proData = getLocData("ProvinceData.json", proType);
            proDatas = new ArrayList<>();
            for (ProvinceData data : proData) {
                proDatas.add(data.getName());
            }
            lvPro.setAdapter(new ArrayAdapter<>(AreaActivity.this, android.R.layout.simple_list_item_1, proDatas));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //初始化另外两个ListView
        cityData = new ArrayList<>();
        cityDatas = new ArrayList<>();
        cityCheckData = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityDatas);
        lvCity.setAdapter(cityAdapter);

        disData = new ArrayList<>();
        disDatas = new ArrayList<>();
        disCheckData = new ArrayList<>();
        disAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, disDatas);
        lvDis.setAdapter(disAdapter);
    }

    /**
     * 省Listview的item点击事件
     *
     * @param position
     */
    @OnItemClick(R.id.lv_pro)
    void selectPro(int position) {
        Log.i("TAG", "Position:" + position);
        cityData.clear();//添加数据前，先删除旧数据
        cityDatas.clear();
        cityCheckData.clear();

        disDatas.clear();  //清除区的数据列表，并通知区更新数据
        disData.clear();
        disCheckData.clear();
        disAdapter.notifyDataSetChanged();
        try {
            int proId = proData.get(position).getProID();    //proId
            Type cityType = new TypeToken<ArrayList<CityData>>() {
            }.getType();
            cityData = getLocData("CityData.json", cityType);
            for (CityData data : cityData) {
                if (data.getProID() == proId) {      //根据proId，查找名下的城市
                    cityDatas.add(data.getName());
                    cityCheckData.add(data);
                }
            }
            cityAdapter.notifyDataSetChanged();    //通知listView更新数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnItemClick(R.id.lv_city)
    void selectCity(int position) {
        Log.i("TAG", "Position:" + position);
        disDatas.clear();
        disData.clear();
        disCheckData.clear();
        try {
            int cityId = cityCheckData.get(position).getCityID();
            Log.i("TAG", "cityId:" + cityId);
            Type disType = new TypeToken<ArrayList<DistrictData>>() {
            }.getType();
            disData = getLocData("DistrictData.json", disType);
            for (DistrictData data : disData) {
                if (data.getCityID() == cityId) {
                    disDatas.add(data.getDisName());
                    disCheckData.add(data);
                }
            }
            disAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 泛型方法，读取assets目录下的json文件，并填充到List中
     *
     * @param fileName
     * @param collectionType
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> List<T> getLocData(String fileName, Type collectionType) throws IOException {
        //获取assets目录下的json文件
        reader = new InputStreamReader(getAssets().open(fileName));
        List<T> locDatas = gson.fromJson(reader, collectionType);
        reader.close();
        return locDatas;
    }
}
