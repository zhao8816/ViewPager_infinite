package com.example.viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ViewPager viewpager;
	private LinearLayout ll;
	int[] resource_image={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
	private ArrayList<ImageView> list_image;
	private ArrayList<ImageView> list_point;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		ll = (LinearLayout) findViewById(R.id.ll);
		//创建集合存放图片
		list_image = new ArrayList<>();
		//创建集合存放小白点
		list_point = new ArrayList<>();
		//初始化数据
		initData();
		//viewpager设置适配器
		viewpager.setAdapter(new MyPageAdapter(list_image));
		//viewpager设置滑动监听
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				/*
				 * for (int i = 0; i < list_point.size(); i++) {
				 *	list_point.get(i).setSelected(false);
				 * }
				 * list_point.get(arg0).setSelected(true);
				 * 
				 * 先让所有的小白点处于为选中状态，再将与当前图片对应的小白点设置为选中状态
				 */
				
				//为了实现无限轮播，在这里拿当前索引arg0对图片总数4取摸，得到的数作为图片的新索引
				int newposition = arg0%list_point.size();
				for (int i = 0; i < list_point.size(); i++) {
					list_point.get(i).setSelected(false);
				}
				list_point.get(newposition).setSelected(true);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//自动轮播
		autoPlay();
		//viewpager设置触摸监听
		viewpager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					//当触摸viewpager时，停止切换页面
					handler.removeCallbacksAndMessages(null);
					break;
				case MotionEvent.ACTION_UP:
					//当离开viewpager时，继续切换页面
					autoPlay();
					break;

				default:
					break;
				}
				return false;
			}
		});
	}
	
	//自动轮播
	private void autoPlay() {
		// TODO Auto-generated method stub
		//每隔两秒向handler发送一条消息，也就是说，每隔两秒切换一张图片
		handler.sendEmptyMessageDelayed(8, 2000);
	}

	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			//收到消息后，播放下一张图片
			viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
			//继续给自己发消息，每隔两秒发一次
			autoPlay();
		};
	};
	//初始化数据
	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < resource_image.length; i++) {
			ImageView imageView=new ImageView(MainActivity.this);
			imageView.setImageResource(resource_image[i]);
			//将图片添加到集合
			list_image.add(imageView);
			//添加小白点
			ImageView view=new ImageView(MainActivity.this);
			view.setImageResource(R.drawable.circle);
			list_point.add(view);
			ll.addView(view);
		}
		list_point.get(0).setSelected(true);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//当activity销毁的时候同时移除handler中的消息
		handler.removeCallbacksAndMessages(null);
	}
}
