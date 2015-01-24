package com.example.groupon;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GetCoordinate implements LocationListener{

	//LocationManagerを変数定義
	LocationManager lm;

	//経度、緯度を格納する変数
	public double lat;
	public double lon;

	public GetCoordinate(){
		/*
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
		//else{
			//if(lm != null){
			//プロバイダーをGPSに設定する。
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
			//}
		//}*/
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		//緯度、経度を取得
		lat = location.getLatitude();
		lon = location.getLongitude();
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

	//経度取得
	public double getlat(){
		return lat;
	}

	//緯度取得
	public double getlon(){
		return lon;
	}

}
