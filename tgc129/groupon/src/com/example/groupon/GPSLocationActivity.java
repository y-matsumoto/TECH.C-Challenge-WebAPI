package com.example.groupon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class GPSLocationActivity extends Activity implements LocationListener{

	//LocationManagerを変数定義
	LocationManager lm;

	//このActivityが生成されたとき、最初に呼び出される。
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpslocation_main);

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
		else{
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
		}
	}

	public void onStart(){
		super.onStart();
	}

	public void onResume(){
		super.onResume();
		if(lm != null){
			//プロバイダーをGPSに設定する。
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
		}
	}

	public void onPause(){
		super.onPause();
		if(lm != null){
			//位置情報の更新が不要になった時の処理
			lm.removeUpdates(this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ

		//TextViewインスタンスの取得
		TextView latitude = (TextView)findViewById(R.id.get_latitude);
		TextView longitude = (TextView)findViewById(R.id.get_longitude);

		//緯度、経度を取得しTextViewに設定
		latitude.setText(String.valueOf(location.getLatitude()));
		longitude.setText(String.valueOf(location.getLongitude()));

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ



	}

}
