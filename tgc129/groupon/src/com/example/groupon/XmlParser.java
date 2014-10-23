package com.example.groupon;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlParser {

	//ReturnCoordinateを定義
	ReturnValue rvalue = new ReturnValue();

	private XmlPullParserFactory factory = null;
	private XmlPullParser parser = null;

	private void XmlParser(){
		try{
			//Xmlパーサー生成
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
		}catch(Exception e){
				
		}

	}


	public void eriaXmlParser(String res){
		try{
			//解析したい内容を設定
			parser.setInput(new StringReader(res));

			//文章の終わりまでループして解析
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				if(eventType == XmlPullParser.START_TAG && parser.getName().equals("code")){
					//Tagの名前がcodeの場合要素を取得
					rvalue.eriacode = parser.getText();
				}else if(eventType == XmlPullParser.START_TAG && parser.getName().equals("cid")){
					//Tagの名前がcidの場合要素を取得
					rvalue.couponid = parser.getText();
				}else{
					//次の要素に進む
					parser.next();
				}

			}

		}catch(Exception e){

		}
	}

	public void couponXmlParser(String resTx2){

	}
}
