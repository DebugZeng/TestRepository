package com.zeng.mytestproject.activity;

import com.zeng.mytestproject.R;
import com.zeng.mytestproject.widget.FeatureAnimationListener;
import com.zeng.mytestproject.widget.ObservableScrollView;
import com.zeng.mytestproject.widget.OnScrollChangedListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/** 
 * @author Author : zengxiantian
 * @version Date：2014-11-12 上午10:45:40 
 * Class Description
 */
public class GuideActivity extends Activity implements OnGlobalLayoutListener,
		OnScrollChangedListener{
	private ObservableScrollView mScrollView;
	private View mAnimView;
	private int mScrollViewHeight;// 滚动条的高度
	private int mStartAnimateTop;// 动画顶部
	private TextView imageView14;// 上拉到最低端，点击开始旅程进入软件首页
	
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_layout);
		init();
		loadView();
		listenerView();
	}
	private void init(){
		context = GuideActivity.this;
	}
	private void loadView(){
		mScrollView = (ObservableScrollView) this
				.findViewById(R.id.scrollView1);
		mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(this);
		mScrollView.setOnScrollChangedListener(this);

		mAnimView = this.findViewById(R.id.anim1);
		mAnimView.setVisibility(View.INVISIBLE);
		
		imageView14 = (TextView) findViewById(R.id.imageView14);
	}
	private void listenerView(){
		imageView14.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GuideActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	boolean hasStart = false;
	/**
	 * 滚动改变的处理方法
	 */
	@Override
	public void onScrollChanged(int top, int oldTop) {
		int animTop = mAnimView.getTop() - top;

		if (top > oldTop) {
			if (animTop < mStartAnimateTop && !hasStart) {
				Animation anim1 = AnimationUtils.loadAnimation(this,
						R.anim.feature_anim2scale_in);
				anim1.setAnimationListener(new FeatureAnimationListener(
						mAnimView, true));

				mAnimView.startAnimation(anim1);
				hasStart = true;
			}
		} else {
			if (animTop > mStartAnimateTop && hasStart) {
				Animation anim1 = AnimationUtils.loadAnimation(this,
						R.anim.feature_alpha_out);
				anim1.setAnimationListener(new FeatureAnimationListener(
						mAnimView, false));

				mAnimView.startAnimation(anim1);
				hasStart = false;
			}
		}
	}
	@Override
	public void onGlobalLayout() {
		mScrollViewHeight = mScrollView.getHeight();
		mStartAnimateTop = mScrollViewHeight / 3 * 2;
	}
}
