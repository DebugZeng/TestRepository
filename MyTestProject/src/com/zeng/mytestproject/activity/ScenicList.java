package com.zeng.mytestproject.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.SimpleAdapter;

import com.zeng.mytestproject.R;
import com.zeng.xlistviewfresh.XListView;
import com.zeng.xlistviewfresh.XListView.IXListViewListener;

/** 
 * @author Author : zengxiantian
 * @version Date��2014-11-14 ����10:53:39 
 * Class Description
 */
public class ScenicList extends Activity implements IXListViewListener{
	private XListView mListView;
	private SimpleAdapter mAdapter1;
	private Handler mHandler;
	private ArrayList<HashMap<String, Object>> dlist;
	
	/** ��ʼ���������� */
	String data[] = new String[] { "���ڻ��ֹ�", "���ݳ�¡��԰", "�����廪��ѧ",
			"�Ϻ�������ѧ", "���Ϸ�˹ų�" };
	String data1[] = new String[] { "���ݳ�¡��԰", "�����廪��ѧ", "��������֮��",
			"�Ϻ�������ѧ", "���ڽ����л�" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scenic_list);
		init();
		loadView();
	}
	private void init(){
		/** ����ˢ�£��������� */
		dlist = new ArrayList<HashMap<String,Object>>();
		mListView = (XListView) findViewById(R.id.techan_xListView);//���listview�������layout����
		mListView.setPullLoadEnable(true);//��������������FALSEΪ�����������㲻���ظ�������
		mAdapter1 = new SimpleAdapter(ScenicList.this, getData(), R.layout.scenic_item_list, new String[] { "name", "img",
						"content" }, new int[] { R.id.title, R.id.img,
						R.id.content });
		mListView.setAdapter(mAdapter1);
		mListView.setXListViewListener(this);
		mHandler = new Handler();
	}
	private ArrayList<HashMap<String, Object>> getData(){
		for (int i = 0; i < data.length; i++) {
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("name", data[i]);
			map.put("content", data1[i]);
			map.put("img", R.drawable.ic_launcher);
			dlist.add(map);
		}
		return dlist;
	}
	/** ֹͣˢ�£� */
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("�ո�");
	}
	private void loadView(){
		
	}
	// ˢ��
		@Override
		public void onRefresh() {
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					getData();
					mListView.setAdapter(mAdapter1);
					onLoad();
				}
			}, 2000);
		}

		// ���ظ���
		@Override
		public void onLoadMore() {
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					getData();
					mAdapter1.notifyDataSetChanged();
					onLoad();
				}
			}, 2000);
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			 if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
	            this.finish();
	         }
	         return false;
		}
}
