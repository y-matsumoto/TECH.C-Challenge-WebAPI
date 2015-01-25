package com.example.testapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	
	static private String mArticleTitle[];
	static private int mArticleNum;

	

	public static void readXML(InputStream stream) 
			throws XmlPullParserException { 
		try {
			XmlPullParser myxmlPullParser = Xml.newPullParser();
			myxmlPullParser. setInput(stream, "UTF-8");
            int cntTitle = 0;
            
            for (int e = myxmlPullParser.getEventType();
            		 e != XmlPullParser.END_DOCUMENT;
            		 e = myxmlPullParser.next()) {
            	if (e == XmlPullParser.START_TAG) { 
            		if (myxmlPullParser.getName().equals("ResultSet")) {
            			mArticleNum = Integer.parseInt(myxmlPullParser.getAttributeValue(null, "totalResultsReturned")); 
                        mArticleTitle = new String[mArticleNum]; 
                    } else if (myxmlPullParser.getName().equals("Title")) { 
                        mArticleTitle[cntTitle] = myxmlPullParser.nextText(); 
                        cntTitle++;
                        }
                    }
             	}
		} catch (XmlPullParserException e) { 
             e.printStackTrace();
		} catch (IOException e) { 
             e.printStackTrace();
     }

	}
	
	 public class ListItem {
         public String title;
         public String url;

         public ListItem(String title, String url) {
                 this.title = title;
                 this.url = url;
         }
	 }
	 

	 
	public static void getArticle(String strURL) { 
        try { 
                URL url = new URL(strURL); 
                URLConnection connection = url.openConnection();
                connection.setDoInput(true); 
                InputStream stream = connection.getInputStream();

                readXML(stream);

                stream.close();
        } catch (Exception e) { 
                e.printStackTrace();
        }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getArticle(createURL());
        ListView list = (ListView) findViewById(R.id.ListView01);
        list.setAdapter( new ArrayAdapter<String>(this, R.layout.rowitem, 
                mArticleTitle));  
	}
	
	




	public static String httpGet(String strURL) { 
        try { 
                URL url = new URL(strURL); 
                URLConnection connection = url.openConnection();

                connection.setDoInput(true); 
                InputStream stream = connection.getInputStream();
                readXML(stream);

                String data = ""; 
                for(int i=0; i<mArticleNum; i++){ 
                        data += mArticleTitle[i]; 
                }

                stream.close();
                return data;
        } catch (Exception e) { 
                return e.toString(); 
        }
	}
	
	public String createURL(){
		String apiURL = "http://chiebukuro.yahooapis.jp/Chiebukuro/V1/categoryTree?appid=dj0zaiZpPVRnNFdXWU9PeHFaaSZzPWNvbnN1bWVyc2VjcmV0Jng9Mzk-";
		return apiURL;
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

