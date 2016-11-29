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
		//�������ϴ��ͼƬ
		list_image = new ArrayList<>();
		//�������ϴ��С�׵�
		list_point = new ArrayList<>();
		//��ʼ������
		initData();
		//viewpager����������
		viewpager.setAdapter(new MyPageAdapter(list_image));
		//viewpager���û�������
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
				 * �������е�С�׵㴦��Ϊѡ��״̬���ٽ��뵱ǰͼƬ��Ӧ��С�׵�����Ϊѡ��״̬
				 */
				
				//Ϊ��ʵ�������ֲ����������õ�ǰ����arg0��ͼƬ����4ȡ�����õ�������ΪͼƬ��������
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
		//�Զ��ֲ�
		autoPlay();
		//viewpager���ô�������
		viewpager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					//������viewpagerʱ��ֹͣ�л�ҳ��
					handler.removeCallbacksAndMessages(null);
					break;
				case MotionEvent.ACTION_UP:
					//���뿪viewpagerʱ�������л�ҳ��
					autoPlay();
					break;

				default:
					break;
				}
				return false;
			}
		});
	}
	
	//�Զ��ֲ�
	private void autoPlay() {
		// TODO Auto-generated method stub
		//ÿ��������handler����һ����Ϣ��Ҳ����˵��ÿ�������л�һ��ͼƬ
		handler.sendEmptyMessageDelayed(8, 2000);
	}

	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			//�յ���Ϣ�󣬲�����һ��ͼƬ
			viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
			//�������Լ�����Ϣ��ÿ�����뷢һ��
			autoPlay();
		};
	};
	//��ʼ������
	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < resource_image.length; i++) {
			ImageView imageView=new ImageView(MainActivity.this);
			imageView.setImageResource(resource_image[i]);
			//��ͼƬ��ӵ�����
			list_image.add(imageView);
			//���С�׵�
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
		//��activity���ٵ�ʱ��ͬʱ�Ƴ�handler�е���Ϣ
		handler.removeCallbacksAndMessages(null);
	}
}
