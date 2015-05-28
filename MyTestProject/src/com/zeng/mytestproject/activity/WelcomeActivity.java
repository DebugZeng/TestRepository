package com.zeng.mytestproject.activity;

import com.zeng.mytestproject.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

/** 
 * @author Author : zengxiantian
 * @version Date：2014-11-12 上午10:17:59 
 * Class Description
 */
public class WelcomeActivity extends Activity {
	private Context context;
	private SharedPreferences preferences;
//	private Editor editor;
	private String isFirst = "";//用来判断是否是第一次进入应用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		init();
		loadView();
		listenerView();
	}
	private void init(){
		context = WelcomeActivity.this;
		preferences = getSharedPreferences("mytestproject_share", 1);
//		editor = preferences.edit();
		
		isFirstCheck();
		
	}
	private void loadView(){
		
	}
	private void listenerView(){
		
	}
	private void isFirstCheck(){
		isFirst = preferences.getString("isfirst", "false");
		if ("true".equals(isFirst)) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						startActivity(new Intent(context,MainActivity.class));
						finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
		}else {
			Editor editor = preferences.edit();
			editor.putString("isfirst", "true");
			editor.commit();
			startActivity(new Intent(context,GuideActivity.class));
			finish();
		}
		
	}
}
