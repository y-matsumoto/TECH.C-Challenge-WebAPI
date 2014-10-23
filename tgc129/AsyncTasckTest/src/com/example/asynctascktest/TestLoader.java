package com.example.asynctascktest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class TestLoader extends AsyncTaskLoader<String>{

	private String EriaUrl = "http://www.groupon.jp/api-v3/?type=area&format=xml"
											+ "&lon=35.85742&lat=139.64893&scope=6";

	//インスタンス生成
	private HttpGet  hget;
	//
	private DefaultHttpClient client;
	//
	private HttpResponse response;
	//
	HttpEntity entity;
	//
	public String resData;

	public TestLoader(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		client = new DefaultHttpClient();
		//
		hget = new HttpGet(EriaUrl);
		//
		HttpResponse response = null;
	}

	@Override
	public String loadInBackground() {
		// TODO 自動生成されたメソッド・スタブ
		//実際の非同期処理

		try{
			//HTTPリクエスト発行
			response = client.execute(hget);
			//ステータスコードの取得
			int status = response.getStatusLine().getStatusCode();
			if(status != HttpStatus.SC_OK){
				//エラー処理
			}else{
				//とりあえずログに表示
				//Log.d("http responce", EntityUtils.toString(response.getEntity(),"UTF-8"));
				//結果を文字列にしてreturn
				entity = response.getEntity();
				resData = EntityUtils.toString(entity, "UTF-8");
				return resData;
			}
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}finally{
			//リソースの解放
			//entity.consumeContent();
			//通信が終了した場合は通信を閉じる
			client.getConnectionManager().shutdown();
		}

		return null;
	}

    @Override
    protected void onStartLoading() {
        // 非同期処理開始前

        // ActivityまたはFragment復帰時（バックキーで戻った、ホーム画面から戻った等）に
        // 再実行されるためここで非同期処理を行うかチェック

        // 非同期処理を開始
    	forceLoad();
    }


    @Override
    protected void onStopLoading() {
        // Loader停止時の処理

        // 非同期処理のキャンセル
        cancelLoad();
    }

    @Override
    public void deliverResult(String data) {
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
