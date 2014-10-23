package com.example.gpstest;

import java.util.ArrayList;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class AsyncTask extends AsyncTaskLoader<ArrayList>{
	
	ArrayList<String> mArrayList = new ArrayList<String>();
	GetLocation
	
	public AsyncTask(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ArrayList loadInBackground() {
		// TODO 自動生成されたメソッド・スタブ
		
		mGetLocation.requestLocation();
		return null;
	}
	
	protected void onStartLoading(){
		forceLoad();
	}
	
	protected void onStoploading(){
		
	}
	
    @Override
    public void deliverResult(ArrayList data) {
        // 登録してあるリスナー（LoaderCallbacksを実装したクラス）に結果を送信する
    	super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        // reset呼び出し時、Loader破棄時の処理
        super.onReset();

        // Loaderを停止
        onStopLoading();
    }
}