package com.mflt.icici;


import android.app.Activity;
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

public class CardsAdapter extends ArrayAdapter<String>{
	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private int lastPosition = -1;
	
	public CardsAdapter(Activity context,
			String[] web, Integer[] imageId){
		super(context, R.layout.adapter, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.adapter, null, true);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.cardimage);
		imageView.setImageResource(imageId[position]);
		imageView.setScaleType(ScaleType.FIT_XY);
		System.out.println(position);
		
		   Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);     
	       lastPosition = position;
	       rowView.startAnimation(animation);
		return rowView;
	}
}