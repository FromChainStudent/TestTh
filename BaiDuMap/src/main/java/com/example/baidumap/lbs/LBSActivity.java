package com.example.baidumap.lbs;

import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.baidumap.R;


@ContentView(R.layout.activity_lbs)
public class LBSActivity extends Activity {

	@ViewInject(R.id.btn_create_table)
	private Button mBtnCreateTable;
	@ViewInject(R.id.btn_add_colums)
	private Button mBtnAddColumns;
	@ViewInject(R.id.btn_add_data)
	private Button mBtnAddData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
	}

	@Event(type = OnClickListener.class, value = { R.id.btn_create_table,
			R.id.btn_add_colums, R.id.btn_add_data })
	private void click(View view) {
		switch (view.getId()) {
		// ������
		case R.id.btn_create_table:
			createTable();
			break;

		case R.id.btn_add_colums:
			addColums();
			break;

		case R.id.btn_add_data:
			addData();
			break;
		}

	}

	// �������
	private void addData() {
		RequestParams params = new RequestParams(
				"http://api.map.baidu.com/geodata/v3/poi/create");
		params.addBodyParameter("title", "HAHA");
		params.addBodyParameter("tags", "С�� �͹� ס��");
		params.addBodyParameter("latitude", "41");
		params.addBodyParameter("longitude", "116");
		params.addBodyParameter("coord_type", "3");
		params.addBodyParameter("geotable_id", "138403");
		params.addBodyParameter("name", "����");
		params.addBodyParameter("ak", "q5nQ7ggEFppDzMnfhokEXQfZjd8rarAv");
		x.http().post(params, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {

			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				Log.i("TAG", "�������ʧ��" + arg0.getMessage());

			}

			@Override
			public void onFinished() {

			}

			@Override
			public void onSuccess(String arg0) {
				Log.i("TAG", "������ݳɹ�"+arg0);

			}
		});

	}

	// ����ֶ�
	private void addColums() {
		RequestParams params = new RequestParams(
				"http://api.map.baidu.com/geodata/v3/column/create");
		params.addBodyParameter("name", "����");
		params.addBodyParameter("key", "name");
		params.addBodyParameter("type", "3");
		params.addBodyParameter("max_length", "100");
		params.addBodyParameter("is_sortfilter_field", "0");
		params.addBodyParameter("is_search_field", "1");
		params.addBodyParameter("Is_index_field", "1");
		params.addBodyParameter("is_unique_field", "1");
		params.addBodyParameter("geotable_id", "138403");
		params.addBodyParameter("ak", "q5nQ7ggEFppDzMnfhokEXQfZjd8rarAv");
		x.http().post(params, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				Log.i("TAG", "����ֶ�ʧ��" + arg0.getMessage());

			}

			@Override
			public void onFinished() {

			}

			@Override
			public void onSuccess(String arg0) {
				Log.i("TAG", arg0);

			}
		});

	}

	// ������
	private void createTable() {
		RequestParams params = new RequestParams(
				"http://api.map.baidu.com/geodata/v3/geotable/create");
		params.addBodyParameter("name", "���Ա�");
		params.addBodyParameter("geotype", "1");
		params.addBodyParameter("is_published", "1");
		params.addBodyParameter("ak", "q5nQ7ggEFppDzMnfhokEXQfZjd8rarAv");
		x.http().post(params, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				Log.i("TAG", arg0.getMessage() + "");

			}

			@Override
			public void onFinished() {

			}

			@Override
			public void onSuccess(String str) {
				Log.i("TAG", str);

			}
		});

	}
}
