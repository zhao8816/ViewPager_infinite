package com.example.viewpager;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPageAdapter extends PagerAdapter {

	private ArrayList<ImageView> list_image;

	public MyPageAdapter(ArrayList<ImageView> list_image) {
		this.list_image = list_image;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		return list_image.size();
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//为了实现无限轮播，在这里那当前索引arg0对图片总数4取摸，得到的数即为图片图片集合中图片的索引，
		//例如，当四张图片全部展示完之后，按说该展示第五张图片了，而集合中没有第五张图片，这是其实是该从头开始展示第一张了，
		//怎么得到第一张图片呢？第五张图片的索引是4,4摸4是0，拿这个0作为索引去集合中找图片，即为所谓的第五张图片，
		//这样就实现了无限轮播
		int newposition = position%list_image.size();
		ImageView view=list_image.get(newposition);
		container.addView(view);
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		int newposition = position%list_image.size();
		container.removeView(list_image.get(newposition));
	}
}
