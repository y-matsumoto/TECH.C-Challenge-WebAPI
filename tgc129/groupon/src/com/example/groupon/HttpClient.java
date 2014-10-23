package com.example.groupon;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpClient{
	//DefaultHttpClientの生成
	static DefaultHttpClient dhc;
	//Getメソッドで接続するルクエストオブジェクトの生成
	static HttpGet hget;

	//コンストラクタ
	public HttpClient(){
		dhc = new DefaultHttpClient();
	}

	/*
	 * サーバ送信
	 */

	public HttpEntity httpGet(String url){

		//
		hget = new HttpGet(url);

		try{
			//リクエストを発行してレスポンスを取得
			HttpResponse hr = dhc.execute(hget);
			return hr.getEntity();
			//ステータスコードのチェック（通信が成功した場合に処理を行う）
			//if(HttpStatus.SC_OK == hr.getStatusLine().getStatusCode()){
				//レスポンス情報の取得
				//HttpEntity entity = hr.getEntity();
			//}

		}
		catch(Exception e){

		}
		finally{
			dhc.getConnectionManager().shutdown();
		}
		return null;
	}

	private void httpPost(){
		//
	}
}
