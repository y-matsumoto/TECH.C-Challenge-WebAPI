package com.example.hotpepper;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

class Summary{
	private String sName;
	private String sCoupon;
	private String sGenre;
	private String sUrl;

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsCoupon() {
		return sCoupon;
	}

	public void setsCoupon(String sCoupon) {
		this.sCoupon = sCoupon;
	}

	public String getsGenre() {
		return sGenre;
	}

	public void setsGenre(String sGenre) {
		this.sGenre = sGenre;
	}

	public String getsUrl() {
		return sUrl;
	}

	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
}

public class HotAsync extends AsyncTaskLoader<List<Summary>>{

	private Summary summary;
	private List<Summary> summaryLst;
	private XmlPullParserFactory xpFactory;
	private XmlPullParser xpp;
	//緯度
	private double longi = 0.0;
	//経度
	private double lati  = 0.0;
	//検索範囲
	private String range;
	private String base = "http://api.hotpepper.jp/GourmetSearch/V110/?key=guest";
	private String url = base + "&Latitude" + lati + "&Longitude" + longi + "&Range" + range;
	private HttpGet hget;
	private DefaultHttpClient client;
	private HttpResponse response;
	HttpEntity entity;


	private String testurl = "http://api.hotpepper.jp/GourmetSearch/V110/?key=guest&Latitude=35.660818&Longitude=139.775426&Range=3";


	public HotAsync(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		client   = new DefaultHttpClient();
		hget     = new HttpGet(testurl);
		response = null;
	}

	@Override
	public List<Summary> loadInBackground() {
		// TODO 自動生成されたメソッド・スタブ
		//非同期処理

		String xmlStr = null;

		/*
		 * Http通信処理
		 */

		try{
			//Httpリクエスト発行
			response = client.execute(hget);
			//ステータスコードの取得
			int status = response.getStatusLine().getStatusCode();
			if(status != HttpStatus.SC_OK){
				//エラー処理
			}else{
				//結果を文字列にする
				entity = response.getEntity();
				xmlStr = EntityUtils.toString(entity, "UTF-8");
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

		/*
		 * XMLパース処理
		 */
		try{
			xpFactory = XmlPullParserFactory.newInstance();
			//XML名前空間に関する処理の許可？
			xpFactory.setNamespaceAware(true);
			xpp = xpFactory.newPullParser();
			xpp.setInput(new StringReader(xmlStr));
		}catch(XmlPullParserException e){
			Log.d("XmlPullParserSample", "Error");
		}

		/*
		 * 解析処理
		 */
		try{
			int eventType;
			eventType = xpp.getEventType();
			summaryLst = new ArrayList<Summary>();
			//XMLの終わりまで繰り返し
			while(eventType != xpp.END_DOCUMENT){
				if(eventType == xpp.START_DOCUMENT){
					Log.d("XmlPullParser", "ParseStart");
				}else if(eventType == xpp.START_TAG && "Shop".equals(xpp.getName())){
					summary = new Summary();
				}else if(eventType == xpp.START_TAG && "ShopName".equals(xpp.getName())){
					//店名を取得
					summary.setsName(xpp.nextText());
				}else if(eventType == xpp.START_TAG && "KtaiCoupon".equals(xpp.getName())){
					//クーポンの有無
					if("0".equals(xpp.nextText())){
						summary.setsCoupon("クーポン有り");
					}
					summary.setsCoupon("クーポン無し");
				}else if(eventType == xpp.START_TAG && "GenreName".equals(xpp.getName())){
					//店ジャンル
					summary.setsGenre(xpp.nextText());
				}else if(eventType == xpp.START_TAG && "ShopUrl".equals(xpp.getName())){
					//ページURL
					summary.setsUrl(xpp.nextText());
				}else if(eventType == xpp.END_TAG && "Shop".equals(xpp.getName())){
					summaryLst.add(summary);
				}
				eventType = xpp.next();
			}
			Log.d("XmlPullParser", "ParseEnd");
		}catch(Exception e){
			Log.d("XmlPullParser","ParseError");
		}
		return summaryLst;
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
    public void deliverResult(List<Summary> data) {
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