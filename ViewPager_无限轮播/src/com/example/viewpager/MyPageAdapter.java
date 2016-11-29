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
		//Ϊ��ʵ�������ֲ����������ǵ�ǰ����arg0��ͼƬ����4ȡ�����õ�������ΪͼƬͼƬ������ͼƬ��������
		//���磬������ͼƬȫ��չʾ��֮�󣬰�˵��չʾ������ͼƬ�ˣ���������û�е�����ͼƬ��������ʵ�Ǹô�ͷ��ʼչʾ��һ���ˣ�
		//��ô�õ���һ��ͼƬ�أ�������ͼƬ��������4,4��4��0�������0��Ϊ����ȥ��������ͼƬ����Ϊ��ν�ĵ�����ͼƬ��
		//������ʵ���������ֲ�
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
