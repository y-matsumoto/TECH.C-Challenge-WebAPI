package com.example.hotpepper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements LoaderCallbacks<String>{

	private Button btn;
	private TextView tv;
	private AsyncResponse mAsyncResponse;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button)findViewById(R.id.btn);
		tv = (TextView)findViewById(R.id.tv);

		//LoaderManagerの初期化
		//getLoaderManager().initLoader(0, null, this);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				MainActivity.this.getLoaderManager().initLoader(0, null, MainActivity.this);
			}
		});
	}


	public void onResume(){
		super.onResume();
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
		//getLoadermanager().initloaderで1回のみ呼び出される
		mAsyncResponse = new AsyncResponse(this);
		return mAsyncResponse;
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
