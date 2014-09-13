package com.example.groupon;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Toast;

public class GPSLocation implements LocationListener{

	//ReturnCoordinateクラスを定義
	ReturnValue rvalue = new ReturnValue();

	//LocationManagerを定義
	LocationManager lm;

	Context context;

	public GPSLocation(Context context){
		this.context = context;
	}

	//
	public void gpsSet(){
		//プロバイダーをGPSに設定する。(100m移動ごとに起爆)
		if(lm != null){
			//最少時間間隔（第2引数）10秒、最少距離間隔(第3引数)10m
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,this);
		}
	}

	//
	public void gpsStop(){
		//位置情報の更新が不要になった時の処理
		if(lm != null){
			lm.removeUpdates(this);
		}
	}


	//GPSのチェック
	public void gpsCheck(){
		//呼び出されたときに、LocationManagerを取得する。
		lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		//GPSの状態の設定を確認
		final boolean gpsEnabled = lm.isProviderEnabled(lm.GPS_PROVIDER);
		//GPSがOFFの時に処理
				if(!gpsEnabled){
					//Toastを設定
					Toast gpsal = Toast.makeText(context, R.string.gpsalert, Toast.LENGTH_LONG);
					//Toastの表示位置を設定
					gpsal.setGravity(Gravity.CENTER,0,0);
					//Toastを表示
					gpsal.show();
					//GPS設定画面への遷移設定
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					//GPS設定画面へ遷移
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		//緯度と経度を取得
		rvalue.longitude = location.getLongitude();
		rvalue.latitude  = location.getLatitude();
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
}
