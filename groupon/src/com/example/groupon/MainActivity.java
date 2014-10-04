package com.example.groupon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity{

	//GPSLocationを定義
	GPSLocation glocation = new GPSLocation(this);

	//ReturnCoordinateを定義
	ReturnValue rvalue = new ReturnValue();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onStart(){
		super.onStart();
		//GPSが使える状態かチェック
		glocation.gpsCheck();
	}

	public void onResume(){
		super.onResume();

		//GPSが使える場合プロバイダー等の情報を設定
		glocation.gpsSet();

		//test用表示処理
		TextView test1 = (TextView)findViewById(R.id.test1);
		TextView test2 = (TextView)findViewById(R.id.test2);
		//TextView test3 = (TextView)findViewById(R.id.test3);

		test1.setText(String.valueOf(rvalue.latitude));
		test2.setText(String.valueOf(rvalue.longitude));
		//test3.setText(String.valueOf())
	}

	public void onPause(){
		super.onPause();
	}

	public void onStop(){
		super.onStop();
		//画面が背面に行った場合、GPSを止める
		glocation.gpsStop();
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
}
