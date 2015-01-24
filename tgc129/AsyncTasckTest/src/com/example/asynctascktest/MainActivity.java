package com.example.asynctascktest;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<String>{

	private static final int LOADER_ID = 0;

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView)findViewById(R.id.test);

		//Loaderに引数を渡す
		//Bundle args = new Bundle();
		//args.putString("hoge", "hoge");

		//Loaderの初期化と開始
		getLoaderManager().initLoader(LOADER_ID,null,this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public Loader<String> onCreateLoader(int id, Bundle args) {
		// TODO 自動生成されたメソッド・スタブ
		//Loaderの生成を行う
		//getLoadermanager().initloaderで1回のみ呼び出される
		TestLoader testloader = new TestLoader(this);
		return testloader;
	}

	@Override
	public void onLoadFinished(Loader<String> loader, String data) {
		// TODO 自動生成されたメソッド・スタブ
		tv.setText(data);
	}

	@Override
	public void onLoaderReset(Loader<String> loader) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
