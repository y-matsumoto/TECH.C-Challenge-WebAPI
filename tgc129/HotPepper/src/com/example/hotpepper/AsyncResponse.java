package com.example.hotpepper;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class AsyncResponse extends AsyncTaskLoader<String>{

	private HttpGet mHttpGet;
	private DefaultHttpClient mClient;
	private HttpEntity mEntity;
	private HttpResponse mResponse;
	private GpsLocation mGpsLocation;
	@SuppressLint("NewApi")
	private String mUrl;

	public AsyncResponse(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		mGpsLocation = new GpsLocation(context);
		String mUrl = "http://api.hotpepper.jp/GourmetSearch/V110/?key=guest"
				+ "&Latitude=" + mGpsLocation.getmLongitude()
				+ "&Longitude=" + mGpsLocation.getmLongitude()
				+ "&Range=3";
		mClient= new DefaultHttpClient();
		mHttpGet = new HttpGet(mUrl);
		mResponse = null;
	}

	@Override
	public String loadInBackground() {
		// TODO 自動生成されたメソッド・スタブ
		//非同期処理

		try{
			mGpsLocation.requestLocation();
			
			//FIXME test
			double l = mGpsLocation.getmLatitude();

			while(mGpsLocation.getmLongitude() == 0.0){
				//強制待ち
			}

			mResponse = mClient.execute(mHttpGet);	//HTTPリクエスト発行
			int status = mResponse.getStatusLine().getStatusCode();	//ステータスコード取得
			if(status != HttpStatus.SC_OK){
				//エラー処理
			}else{

			mEntity = mResponse.getEntity();	//結果を文字列にしてreturn
				String resData = EntityUtils.toString(mEntity, "UTF-8");
				return resData;
			}
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}finally{
			//mEntity.consumeContent();	//リソースの解放
			mClient.getConnectionManager().shutdown();	//通信が終了した場合は通信を閉じる
		}
		return null;
	}

	@Override
	protected void onStartLoading() {
		// Loaderを開始前に呼び出される
		forceLoad();
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onStopLoading() {
	    // Loader停止時の処理

	    // 非同期処理のキャンセル
	        cancelLoad();
	}

	@Override
	public void deliverResult(String data) {
	    // LoaderCallbacksに結果を送信
		super.deliverResult(data);
	}

	@Override
	    protected void onReset() {
        // Loader破棄時の処理
        super.onReset();

        // Loaderを停止
        onStopLoading();
	}

}
