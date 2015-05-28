package com.zeng.mytestproject.activity;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;

import com.zeng.mytestproject.R;
import com.zeng.mytestproject.adapter.ImageAdapter;
import com.zeng.mytestproject.widget.CircleFlowIndicator;
import com.zeng.mytestproject.widget.OnScrollChangedListener;
import com.zeng.mytestproject.widget.ViewFlow;

public class MainActivity extends Activity implements OnGlobalLayoutListener,
		OnScrollChangedListener{
	private ViewFlow viewFlow;
	private ImageButton jingquBtn;

	private ImageAdapter imageAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		loadView();
		listenerView();
		setData();
	}
	private void init(){
	}
	private void loadView(){
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		jingquBtn = (ImageButton) findViewById(R.id.jingqu_btn);
	}
	private void listenerView(){
		jingquBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						SceneryGuideList.class);
				startActivity(intent);
			}
		});
	}
	private void setData(){
		imageAdapter = new ImageAdapter(this);
		viewFlow.setAdapter(imageAdapter);
		viewFlow.setmSideBuffer(3); // 实际图片张数， 我的ImageAdapter实际图片张数为3
		
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(3 * 1000); // 设置初始位置
		viewFlow.startAutoFlowTimer(); // 启动自动播放
	}
	@Override
	public void onScrollChanged(int top, int oldTop) {
		
	}
	@Override
	public void onGlobalLayout() {
		
	}

}
