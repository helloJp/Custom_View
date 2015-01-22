package me.jp.load;

import java.util.ArrayList;

import me.jp.adapter.MyAdapter;
import me.jp.entity.ApkEntity;
import me.jp.load.R;
import me.jp.view.LoadListView;
import me.jp.view.LoadListView.ILoadMoreListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity implements ILoadMoreListener {
	ArrayList<ApkEntity> apk_list = new ArrayList<ApkEntity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getData();
		showListView(apk_list);
	}

	MyAdapter adapter;
	LoadListView listview;

	private void showListView(ArrayList<ApkEntity> apk_list) {
		if (adapter == null) {
			listview = (LoadListView) findViewById(R.id.listview);
			listview.setOnLoadMoreListener(this);
			adapter = new MyAdapter(this, apk_list);
			listview.setAdapter(adapter);
		} else {
			adapter.onDateChange(apk_list);
		}
	}

	private void getData() {
		for (int i = 0; i < 10; i++) {
			ApkEntity entity = new ApkEntity();
			entity.setName("测试程序");
			entity.setInfo("500w用户");
			entity.setDes("这是一个神奇的App！");
			apk_list.add(entity);
		}
	}

	private void getLoadData() {
		for (int i = 0; i < 2; i++) {
			ApkEntity entity = new ApkEntity();
			entity.setName("更多程序");
			entity.setInfo("500w用户");
			entity.setDes("这是一个神奇的App！");
			apk_list.add(entity);
		}
	}

	@Override
	public void onLoad() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 获取更多数据
				getLoadData();
				// 更新listview显示；
				showListView(apk_list);
				// 通知listview加载完毕
				listview.loadComplete();
			}
		}, 2000);
	}
}
