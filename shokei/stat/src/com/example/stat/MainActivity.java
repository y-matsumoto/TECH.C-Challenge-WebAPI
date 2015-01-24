package com.example.stat;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mTextView;
	private TextView mTextView1;
	private TextView mTextView2;
	private TextView mTextView3;
	private TextView mTextView4;
	private Button mButton;
	private EditText mEditText;
	String URL = "http://www.drk7.jp/weather/xml/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitAll().build());

		mTextView = (TextView) findViewById(R.id.textView);
		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);
		mTextView3 = (TextView) findViewById(R.id.textView3);
		mTextView4 = (TextView) findViewById(R.id.textView4);
		mEditText = (EditText) findViewById(R.id.editText);
		
		mButton = (Button) findViewById(R.id.button);

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				
				String input = mEditText.getText().toString();
				
				String url = URL + input + ".xml";
				String data = HttpAccess(url);
				String pref = null;
				String area = null;
				String weather = null;
				String max_temp = null;
				String min_temp = null;
				String info = null;
				mTextView.setText(url);
				if (data != null) {
					XmlPullParser xmlPullParser = Xml.newPullParser();

					// Wentity entity = new Wentity();

					try {
						xmlPullParser.setInput(new StringReader(data));
					} catch (XmlPullParserException e) {
						Log.d("XmlPullParserSample", "Error");
					}

					List<String> prefList = new ArrayList<String>();
					List<String> areaList = new ArrayList<String>();
					List<String> weatherList = new ArrayList<String>();
					List<String> tempList = new ArrayList<String>();
					List<String> infoList = new ArrayList<String>();

					try {
						int eventType;
						eventType = xmlPullParser.getEventType();
						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {
								if (xmlPullParser.getName().equals("pref")) {
									pref = xmlPullParser.getAttributeValue(
											null, "id");
									prefList.add(pref);
								} else if (xmlPullParser.getName().equals(
										"area")) {
									area = xmlPullParser.getAttributeValue(
											null, "id");
									areaList.add(area);
								} else if (xmlPullParser.getName().equals(
										"info")) {
									info = xmlPullParser.getAttributeValue(
											null, "date");
									infoList.add(info);
								} else if (xmlPullParser.getName().equals(
										"weather")) {
									weather = xmlPullParser.nextText();
									weatherList.add(weather);
								} else if (xmlPullParser.getName().equals(
										"range")) {
									max_temp = xmlPullParser.getAttributeValue(
											null, "max");
									tempList.add(max_temp);
									min_temp = xmlPullParser.getAttributeValue(
											null, "min");
									tempList.add(min_temp);
								}
							}

							eventType = xmlPullParser.next();

						}
					} catch (Exception e) {
						mTextView.setText("XmlPullParserSample");
					}

					pref = prefList.toString();

					String[] a = areaList.toArray(new String[0]);

					String[] w = weatherList.toArray(new String[0]);

					String[] i = infoList.toArray(new String[0]);

					mTextView.setText(pref);

					int x = 0;
					
					mTextView1.setText(a[x] + w[x] + i[x]);
					mTextView2.setText(a[x] + w[x + 1] + i[x+1]);
					mTextView3.setText(a[x] + w[x + 2] + i[x+2]);
					mTextView4.setText(a[x] + w[x + 3] + i[x+3]);

				}
			}
		});

	}

	public String HttpAccess(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response;

		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return null;

			HttpEntity entity = response.getEntity();
			String data = EntityUtils.toString(entity, "utf-8");
			return data;
		} catch (Exception e) {
			// noop
		}
		return null;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
