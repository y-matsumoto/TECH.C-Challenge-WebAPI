package com.example.gpssample;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GpsLocation{
	
	private double mLongitude;
	private double mLatitude;
	private TimeoutTimer mTimeoutTimer;
	private LocationManager mLocationManager;
	private Criteria mCriteria;
	
	
	public GpsLocation(Context context){
		mLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
		mCriteria = new Criteria();
		mCriteria.setAccuracy(Criteria.ACCURACY_HIGH);//精度
		mCriteria.setPowerRequirement(Criteria.POWER_HIGH);//消費電力
		mCriteria.setBearingRequired(false);//方位情報
		mCriteria.setSpeedRequired(false);//速度
		mCriteria.setAltitudeRequired(false);//高度
	}
	
	public void request(){
		mLocationManager.requestSingleUpdate(mCriteria, new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				// TODO 自動生成されたメソッド・スタブ
				setmLongitude(location.getLongitude());
				setmLatitude(location.getLatitude());
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
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
			
		}, null);
	}
	
	/*
	 * getter・setter
	 */
	
	public double getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}

	public double getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}
	

}
