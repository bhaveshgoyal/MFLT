package com.mflt.icici;


import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class CardsAdapter extends ArrayAdapter<String>{
	private final Activity context;
	private final ArrayList<String> web;
	private final ArrayList<Integer> imageId;
	private final ArrayList<String> infolist;
	private int lastPosition = -1;
	
	public CardsAdapter(Activity context,
			ArrayList<String> web, ArrayList<Integer> imageId, ArrayList<String> infolist){
		super(context, R.layout.adapter, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.infolist = infolist;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view == null){
			// inflate the layout
			LayoutInflater inflater = context.getLayoutInflater();
			view= inflater.inflate(R.layout.adapter, parent, false);
		}
		
		ImageView imageView = (ImageView) view.findViewById(R.id.cardimage);
		TextView text = (TextView)view.findViewById(R.id.info);
//		SharedPreferences prefs = view.getSharedPreferences("ICICI_PREFS", context.MODE_PRIVATE);
		text.setText(infolist.get(position));
		
		imageView.setImageResource(imageId.get(position));
		imageView.setScaleType(ScaleType.FIT_XY);
//		System.out.println(position);
		
		   Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);     
	       lastPosition = position;
	       view.startAnimation(animation);
		return view;
	}
}