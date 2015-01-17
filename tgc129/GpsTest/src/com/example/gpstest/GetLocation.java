package com.example.gpstest;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GetLocation implements LocationListener{

	private LocationManager mLocationManager;
	static double mLongitude;
	static double mLatitude;
	static Location mLocation;
	static Context mContext;
	private Criteria mCriteria;

	public GetLocation(Context context){
		this.mContext = context;
		mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		mCriteria = new Criteria();
		mCriteria.setAltitudeRequired(false);//標高の取得（不要）
		mCriteria.setBearingRequired(false);//進行方向の取得（不要）
		mCriteria.setCostAllowed(false);//費用が掛かるかどうかの取得（不要）
		mCriteria.setSpeedRequired(false);//速度の取得（不要）
		//masync = new Async(context);
	}

	//位置情報取得のリクエストの発行
	public void requestLocation(){
		if(mLocationManager != null){
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
		}
	}


	/*位置情報取得リクエストの中止
	public void stopRequestLocation(){
		if(mLocationManager != null){
			mLocationManager.removeUpdates(this);
		}
	}*/

	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		mLongitude = location.getLongitude();
		mLatitude  = location.getLatitude();

		//緯度、経度が取れたらすぐに更新を中止させる。
		mLocationManager.removeUpdates(this);
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
