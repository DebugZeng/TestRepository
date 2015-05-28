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
 * @version Date��2014-11-13 ����11:04:42 
 * Class Description
 */
public class SceneryGuideList extends ActivityGroup {
	private ViewPager viewPager;
	private ImageView imageView;
	private TextView listGuide, mapGuide;
	private List<View> views;
	private int offset = 0;// ����ͼƬ��ƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ͼƬ���
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
		// views.add(view1);//��View1������Activity �����������µ�Activity���洦��ҳ��Ҫ���ص��߼�
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
	 * ��ʼ���������������ҳ������ʱ������ĺ���Ҳ������Ч������������Ҫ����һЩ����
	 */
	private void InitImageView() {
		// TODO Auto-generated method stub
		imageView = (ImageView) findViewById(R.id.iv_bottom_line);
		bmpW = imageView.getLayoutParams().width;// ��ȡ�ײ������ߵĿ��
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 2 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
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
		int one = offset * 2 + bmpW;// ҳ��1-->ҳ��2 ƫ����
		int two = offset * 2;// ҳ��2-->ҳ��3 ƫ����

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
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			imageView.startAnimation(animation);
			// Toast.makeText(SceneryGuideList.this,
			// "��ѡ����" + viewPager.getCurrentItem() + "ҳ��",
			// Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * �������ذ���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			this.finish();
		}
		return false;
	}

}
