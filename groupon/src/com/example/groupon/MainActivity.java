package com.example.groupon;

import org.apache.http.HttpEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener{

	//LocationManagerを変数定義
	LocationManager lm;
	//接続先URL
	static String url = "http://www.groupon.jp/api-v3/?type=area&format=xml";
	//緯度
	static double longitude;
	//経度
	static double latitude;
	//スコープ
	static int scope = 6;
	//HttpClient
	HttpClient httpclient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//呼び出されたときに、LocationManagerを取得する。
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//GPSの状態の設定
		final boolean gpsEnabled = lm.isProviderEnabled(lm.GPS_PROVIDER);
		//GPSがOFFの時に処理
		if(!gpsEnabled){
			//Toastを設定
			Toast gpsal = Toast.makeText(this, R.string.gpsalert, Toast.LENGTH_LONG);
			//Toastの表示位置を設定
			gpsal.setGravity(Gravity.CENTER,0,0);
			//Toastを表示
			gpsal.show();
			//GPS設定画面への遷移設定
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			//GPS設定画面へ遷移
			startActivity(intent);
		}
		//Bundleにパラメータを保存
		//Bundle bundle = new Bundle();

	}

	public void onResume(){
		super.onResume();
		if(lm != null){
			//プロバイダーをGPSに設定する。(100m移動ごとに起爆)
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,100,this);
		}
	}

	public void onPause(){
		super.onPause();
		if(lm != null){
			//位置情報の更新が不要になった時の処理
			lm.removeUpdates(this);
		}
	}

	//座標が変更されたときに呼び出される。
	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		//緯度と経度を取得
		latitude  = location.getLatitude();
		longitude = location.getLongitude();

		//完成版URL
		String dataurl = url + "&lon=" + longitude + "&lat=" + latitude + "&scope=" + scope;

		httpclient = new HttpClient();
		HttpEntity entity = httpclient.httpGet(dataurl);


		//TextView accessurl = (TextView)findViewById(R.id.test1);
		//TextView lon = (TextView)findViewById(R.id.test2);
		//TextView lat = (TextView)findViewById(R.id.test3);
		//lon.setText(latitude);
		//lat.setText(longitude);
		//accessurl.setText(dataurl);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

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
