package com.example.groupon;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

public class ServerCom extends AsyncTaskLoader<String>{

	//ReturnValueクラス生成
	ReturnValue rvalue = new ReturnValue();

	//scope(後でユーザーが選べるようにする)
	int scope = 6;

	//エリア取得のURL
	private String EriaUrl = "http://www.groupon.jp/api-v3/?type=area&format=xml"
								+ "&lon=" + rvalue.longitude
								+ "&lat=" + rvalue.latitude
								+ "&scope=" + scope;

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

	public ServerCom(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		//
		 client = new DefaultHttpClient();
		//
		hget = new HttpGet(EriaUrl);
		//
		HttpResponse response = null;
	}

	@Override
	public String loadInBackground() {
		// TODO 自動生成されたメソッド・スタブ
		try{
			//HTTPリクエスト発行
			response = client.execute(hget);
			//ステータスコードの取得
			int status = response.getStatusLine().getStatusCode();
			if(status != HttpStatus.SC_OK){
				//エラー処理
			}else{
				//とりあえずログに表示
				Log.d("http responce", EntityUtils.toString(response.getEntity(),"UTF-8"));
				//結果を文字列にしてreturn
				entity = response.getEntity();
				resData = EntityUtils.toString( entity, "UTF-8");
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
}
