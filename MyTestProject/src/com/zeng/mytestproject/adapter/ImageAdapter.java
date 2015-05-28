package com.zeng.mytestproject.adapter;


import com.zeng.mytestproject.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author Author : zengxiantian
 * @version Date：2014-11-13 上午10:38:09 
 * Class Description
 */
public class ImageAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private static final int[] ids = {R.drawable.test1, R.drawable.test2, R.drawable.test3 };
	
	public ImageAdapter(Context context){
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;//返回很大的值使得getView中的position不断增大来实现循环
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View converView, ViewGroup parent) {
		if (converView == null) {
			converView = inflater.inflate(R.layout.image_item, null);
		}
		((ImageView) converView.findViewById(R.id.imgView)).setImageResource(ids[position%ids.length]);
		/*converView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("image_id", ids[position%ids.length]);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});*/
		return converView;
	}

}
