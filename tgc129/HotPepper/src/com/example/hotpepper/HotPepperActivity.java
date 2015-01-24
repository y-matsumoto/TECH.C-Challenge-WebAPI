package com.example.hotpepper;

import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

public class HotPepperActivity extends Activity implements LoaderCallbacks<List<Summary>>{

	private static final int LOADER_ID = 0;
	private ListView lv;
	private Button btn;
	private CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_pepper);

		lv = (ListView)findViewById(R.id.listview);
		btn = (Button)findViewById(R.id.btn);

		getLoaderManager().initLoader(LOADER_ID,null,this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hot_pepper, menu);
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
	public Loader<List<Summary>> onCreateLoader(int id, Bundle args) {
		// TODO 自動生成されたメソッド・スタブ
		HotAsync hotasync= new HotAsync(this);
		return hotasync;
	}

	@Override
	public void onLoadFinished(Loader<List<Summary>> loader, List<Summary> data) {
		// TODO 自動生成されたメソッド・スタブ
		//adapter = new CustomAdapter(this,data);
		lv.setAdapter(new CustomAdapter(this,data));
	}

	@Override
	public void onLoaderReset(Loader<List<Summary>> loader) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
