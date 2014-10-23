package com.example.groupon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListViewActivity extends Activity{

	ListView lv;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		
		/*
		//ArrayAdapterにリスト表示したい文字列の配列を格納
		ArrayAdapter<String> adapter = 
				//ArrayAdapter(Context,TextViewに対するソース,配列のオブジェクト
				new ArrayAdapter<String>(this,android.R.layout.,//リスト)
		*/
		
		//ListViewにArrayAdapterの内容を渡す。
		//lv.setAdapter(adapter);
		}
}
