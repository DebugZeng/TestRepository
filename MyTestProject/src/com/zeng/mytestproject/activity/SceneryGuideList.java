package com.zeng.mytestproject.activity;

import java.util.ArrayList;
import java.util.List;

import com.zeng.mytestproject.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author Author : zengxiantian
 * @version Date：2014-11-13 上午11:04:42 
 * Class Description
 */
public class SceneryGuideList extends ActivityGroup {
	private ViewPager viewPager;
	private ImageView imageView;
	private TextView listGuide, mapGuide;
	private List<View> views;
	private int offset = 0;// 动画图片便偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 图片宽度
	private View view1, view2, view3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scenic_guide_list);
		InitImageView();
		InitTextView();
		InitViewPager();

	}

	@SuppressWarnings("deprecation")
	private void InitViewPager() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.scenic_list, null);
		view2 = inflater.inflate(R.layout.scenic_map_list, null);
		// views.add(view1);//把View1换成了Activity 这样就能在新的Activity里面处理页面要加载的逻辑
		views.add(this
				.getLocalActivityManager()
				.startActivity(
						"1",
						new Intent(SceneryGuideList.this, ScenicList.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());
		views.add(this
				.getLocalActivityManager()
				.startActivity(
						"2",
						new Intent(SceneryGuideList.this,
								ScenicGuideMapList.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private void InitTextView() {
		// TODO Auto-generated method stub
		listGuide = (TextView) findViewById(R.id.list_guide);
		mapGuide = (TextView) findViewById(R.id.map_guide);
		listGuide.setOnClickListener(new MyOnClick(0));
		mapGuide.setOnClickListener(new MyOnClick(1));
	}

	/**
	 * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
	 */
	private void InitImageView() {
		// TODO Auto-generated method stub
		imageView = (ImageView) findViewById(R.id.iv_bottom_line);
		bmpW = imageView.getLayoutParams().width;// 获取底部滑动线的宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 2 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
	}

	class MyOnClick implements OnClickListener {
		int index = 0;

		MyOnClick(int i) {
			this.index = i;
		}

		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}

	}

	class MyViewPagerAdapter extends PagerAdapter {
		private List<View> listViews;

		MyViewPagerAdapter(List<View> listViews) {
			this.listViews = listViews;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(listViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(listViews.get(position), 0);
			return listViews.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpW;// 页卡1-->页卡2 偏移量
		int two = offset * 2;// 页卡2-->页卡3 偏移量

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(one * currIndex, one
					* arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			imageView.startAnimation(animation);
			// Toast.makeText(SceneryGuideList.this,
			// "您选择了" + viewPager.getCurrentItem() + "页卡",
			// Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 监听返回按键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			this.finish();
		}
		return false;
	}

}
