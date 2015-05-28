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
 * @version Date：2014-11-14 上午10:53:39 
 * Class Description
 */
public class ScenicList extends Activity implements IXListViewListener{
	private XListView mListView;
	private SimpleAdapter mAdapter1;
	private Handler mHandler;
	private ArrayList<HashMap<String, Object>> dlist;
	
	/** 初始化本地数据 */
	String data[] = new String[] { "深圳欢乐谷", "广州长隆公园", "北京清华大学",
			"上海复旦大学", "湖南凤凰古城" };
	String data1[] = new String[] { "广州长隆公园", "北京清华大学", "深圳世界之窗",
			"上海复旦大学", "深圳锦绣中华" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scenic_list);
		init();
		loadView();
	}
	private void init(){
		/** 下拉刷新，上拉加载 */
		dlist = new ArrayList<HashMap<String,Object>>();
		mListView = (XListView) findViewById(R.id.techan_xListView);//这个listview是在这个layout里面
		mListView.setPullLoadEnable(true);//设置让它上拉，FALSE为不让上拉，便不加载更多数据
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
	/** 停止刷新， */
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	private void loadView(){
		
	}
	// 刷新
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

		// 加载更多
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
