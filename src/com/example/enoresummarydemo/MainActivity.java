package com.example.enoresummarydemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.enoresummarydemo.fragment.BaseFragment;
import com.example.enoresummarydemo.fragment.fram.FramFragment;
import com.example.enoresummarydemo.fragment.views.ViewFragment;

/**
 * 
 * @author Encore
 * 
 */
public class MainActivity extends BaseActivity implements OnCheckedChangeListener {

	/**
	 * Fragment TabHost 对象
	 */
	private FragmentTabHost mFragmentTabHost;
	/**
	 * 切换tab控件
	 */
	private RadioGroup mTabs;
	/**
	 * 最后选中的fragment
	 */
	public BaseFragment mLastFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setIsInnitSwipeBack(false); // 在super oncreate 前调用是否启动滑动后退的插件
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化UI
		initUI();

		initFristFragment();
	}

	/**
	 * 初始化UI
	 */
	public void initUI() {
		mTabs = (RadioGroup) findViewById(R.id.tab);
		mTabs.setOnCheckedChangeListener(this);
	}

	/**
	 * 显示fristFragment
	 */
	public void initFristFragment() {
		// 初始化第一个fragment
		mTabs.check(R.id.rboFram);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		String Tag = String.valueOf(checkedId);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		BaseFragment newFragment = ((BaseFragment) getSupportFragmentManager().findFragmentByTag(Tag));

		boolean isNew = false;
		if (newFragment == null) {
			isNew = true;
			switch (checkedId) {
			case R.id.rboFram: {
				newFragment = new FramFragment();
				break;
			}
			case R.id.rboViews: {
				newFragment = new ViewFragment();
				break;
			}
			}
		}

		if (newFragment == null) {
			Toast.makeText(MainActivity.this, "No tab known for tag " + checkedId, Toast.LENGTH_SHORT).show();
			return;
		}

		if (newFragment != mLastFragment) {
			if (mLastFragment != null) {
				ft.detach(mLastFragment);
			}
			if (isNew) {
				ft.add(R.id.container, newFragment, Tag);
			} else {
				ft.attach(newFragment);
			}
			mLastFragment = newFragment;
		}
		ft.commit();
		getSupportFragmentManager().executePendingTransactions();
	}

}
